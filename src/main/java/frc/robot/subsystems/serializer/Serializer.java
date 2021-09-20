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
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class Serializer extends SubsystemBase implements ISerializer {

  private CANSparkMax turnTable;

  public enum Speed {
    fast, slow
  }

  private CANPIDController m_pidController;
  private CANEncoder m_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
  private double setPoint;
  private final double FAST_SPEED = 2200;
  private final double SLOW_SPEED = 600;
  private double manualSpeedTarget;
  private double rotationLength;

  final boolean tuning = false;

  public Serializer() {
    turnTable = new CANSparkMax(RobotConstants2020.TURN_TABLE_SPARKMAX, CANSparkMax.MotorType.kBrushless);
    // turnTable.setInverted(true);
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
    /* m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput); */
    if (tuning) {
    

      // display PID coefficients on SmartDashboard
      SmartDashboard.putNumber("P Gain Serializer", kP);
      SmartDashboard.putNumber("I Gain Serializer", kI);
      SmartDashboard.putNumber("D Gain Serializer", kD);
    }
    /*
     * SmartDashboard.putNumber("I Zone Serializer", kIz);
     * SmartDashboard.putNumber("Feed Forward Serializer", kFF);
     * SmartDashboard.putNumber("Max Output Serializer", kMaxOutput);
     * SmartDashboard.putNumber("Min Output Serializer", kMinOutput);
     */

    SmartDashboard.putNumber("Serializer Target", manualSpeedTarget);
    rotationLength = 11.202576;
  }

  // private double turnTableDegrees(CANSparkMax input) {
  //   double gearRatio = (double) 10 / 1; // ratio of the axel the turntable lies on to the axel the encoder reads
  //   int ticksPerRev = 42; // amount of ticks in one revolution of the encoder axel
  //   double counts = input.getEncoder().getPosition();
  //   return (counts * gearRatio) / ticksPerRev;
  // }

  public void driveTurnTable(SerializerSpeed speed) {
    // read PID coefficients from SmartDashboard
    if (tuning) {
      double p = SmartDashboard.getNumber("P Gain Serializer", 0);
      double i = SmartDashboard.getNumber("I Gain Serializer", 0);
      double d = SmartDashboard.getNumber("D Gain Serializer", 0);

      /*
      double iz = SmartDashboard.getNumber("I Zone Serializer", 0);
      double ff = SmartDashboard.getNumber("Feed Forward Serializer", 0);
      double max = SmartDashboard.getNumber("Max Output Serializer", 0);
      double min = SmartDashboard.getNumber("Min Output Serializer", 0);
      */

      // if PID coefficients on SmartDashboard have changed, write new values to
      // controller
      if ((p != kP)) {
        m_pidController.setP(p);
        kP = p;
      }
      if ((i != kI)) {
        m_pidController.setI(i);
        kI = i;
      }
      if ((d != kD)) {
        m_pidController.setD(d);
        kD = d;
      }
      /*
      if ((iz != kIz)) {
        m_pidController.setIZone(iz);
        kIz = iz;
      }
      if ((ff != kFF)) {
        m_pidController.setFF(ff);
        kFF = ff;
      }
      if ((max != kMaxOutput) || (min != kMinOutput)) {
        m_pidController.setOutputRange(min, max);
        kMinOutput = min;
        kMaxOutput = max;
      }
      */
    }

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
    manualSpeedTarget = SmartDashboard.getNumber("Serializer Target", 0);
    m_pidController.setReference(manualSpeedTarget, ControlType.kVelocity);

  }

  public void unJam() {
    turnTable.set(-0.4); // TODO: check with Dan for magnitude
  }

  @Override
  public void periodic() {
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
