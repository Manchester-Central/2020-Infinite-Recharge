/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.serializer;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.dashboard.NumberDashboardValue;
import frc.robot.util.dashboard.PIDDashboardUpdater;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class Serializer extends SubsystemBase implements ISerializer{

  private static final String DASHBOARD_KEY = "Serializer";

  private CANSparkMax turnTable;

  public enum Speed {
    fast, slow
  }

  private CANPIDController m_pidController;
  private CANEncoder m_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
  private double setPoint;
  private final double FAST_SPEED = 2500;
  private final double SLOW_SPEED = 425;
  private double manualSpeedTarget;
  private double rotationLength;
  private PIDDashboardUpdater pidDashboardValues;

  private NumberDashboardValue serializerTargetSpeed;

  final boolean tuning = false;

  public Serializer() {
    turnTable = new CANSparkMax(RobotConstants2020.TURN_TABLE_SPARKMAX, CANSparkMax.MotorType.kBrushless);
    //turnTable.setInverted(true);
    m_encoder = turnTable.getEncoder();
    // m_encoder.setInverted(true);
    
    /**
     * The RestoreFactoryDefaults method can be used to reset the configuration
     * parameters in the SPARK MAX to their factory default state. If no argument is
     * passed, these parameters will not persist between power cycles
     */
    turnTable.restoreFactoryDefaults();

    /**
     * In order to use PID functionality for a controller, a CANPIDController object
     * is constructed by calling the getPIDController() method on an existing
     * CANSparkMax object
     */
    m_pidController = turnTable.getPIDController();
    // Encoder object created to display position values

    // PID coefficients
    kP = 0.00013;
    kI = 0.0;
    kD = 0;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 1;
    kMinOutput = -1;
    maxRPM = 1500;
    setPoint = 0;
    manualSpeedTarget = 900;

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    pidDashboardValues = PIDDashboardUpdater.createDashboardPID(m_pidController, DASHBOARD_KEY, kP, kI, kD);
    // Log the PID values mostly to avoid an unused warning on pidDashboardValues
    pidDashboardValues.logPID();

    /* m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput); */
    /* SmartDashboard.putNumber("I Zone Serializer", kIz);
    SmartDashboard.putNumber("Feed Forward Serializer", kFF);
    SmartDashboard.putNumber("Max Output Serializer", kMaxOutput);
    SmartDashboard.putNumber("Min Output Serializer", kMinOutput); */
    serializerTargetSpeed = new NumberDashboardValue(DASHBOARD_KEY + "/Target Speed", manualSpeedTarget);

    rotationLength = 11.202576;
  }

  private double turnTableDegrees(CANSparkMax input) {
    double gearRatio = (double) 10 / 1; // ratio of the axel the turntable lies on to the axel the encoder reads
    int ticksPerRev = 42; // amount of ticks in one revolution of the encoder axel
    double counts = input.getEncoder().getPosition();
    return (counts * gearRatio) / ticksPerRev;
  }

  public void driveTurnTable(SerializerSpeed speed) {
    switch (speed) {
      case fast:
      setPoint = FAST_SPEED;
      break;
      case slow:
      setPoint = SLOW_SPEED;
      break;
      case stop:
      setPoint = 0;
      break;
    }

    /**
     * PIDController objects are commanded to a set point using the SetReference()
     * method.
     * 
     * The first parameter is the value of the set point, whose units vary depending
     * on the control type set in the second parameter.
     * 
     * The second parameter is the control type can be set to one of four
     * parameters: com.revrobotics.ControlType.kDutyCycle
     * com.revrobotics.ControlType.kPosition com.revrobotics.ControlType.kVelocity
     * com.revrobotics.ControlType.kVoltage
     */
    m_pidController.setReference(setPoint, ControlType.kVelocity);
  }

  public void manualSpeed(boolean on) {
    if (!on) {
      turnTable.set(0);
      return;
    }
    manualSpeedTarget = serializerTargetSpeed.get();
    m_pidController.setReference(manualSpeedTarget, ControlType.kVelocity);
    
  }

  public void unJam() {
    turnTable.set(-0.4); // TODO: check with Dan for magnitude 
  }

  @Override
  public void periodic(){
    SmartDashboard.putNumber("SerializerSetpoint", setPoint);
    SmartDashboard.putNumber("ManualSpeedTarget", manualSpeedTarget);
    SmartDashboard.putNumber("Serializer Encoder", m_encoder.getVelocity());
    SmartDashboard.putNumber("Serializer Position", m_encoder.getPosition());
  }

  @Override
  public double getPosition() {
    return m_encoder.getPosition() / rotationLength;
  }
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
}
