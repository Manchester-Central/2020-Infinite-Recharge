/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.flywheel;

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
public class Flywheel extends SubsystemBase implements IFlywheel {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  final boolean tuning = false;
  public final String FLYWHEEL_TARGET = "Flywheel target speed RPM";
  public final int FLYWHEEL_DEFAULT = 3000;
  public double setPoint = FLYWHEEL_DEFAULT;

  private CANPIDController m_pidControllerA, m_pidControllerB;
  private CANSparkMax flywheelA, flywheelB;
  private CANEncoder m_encoder;

  // PID coefficients
  public double kP = 0.0002;
  public double kI = 0;
  public double kD = 0.0002;
  public double kIz = 0;
  public double kFF = 0;
  public double maxRPM = 5676;
  public double kMinOutput = 0;
  public double kMaxOutput = 1;

  public Flywheel() {
    flywheelA = new CANSparkMax(RobotConstants2020.FLYWHEELA_SPARKMAX, CANSparkMax.MotorType.kBrushless);
    flywheelB = new CANSparkMax(RobotConstants2020.FLYWHEELB_SPARKMAX, CANSparkMax.MotorType.kBrushless);

    flywheelA.restoreFactoryDefaults();
    flywheelB.restoreFactoryDefaults();

    flywheelA.setInverted(false);
    flywheelB.setInverted(true);

    flywheelA.setIdleMode(CANSparkMax.IdleMode.kCoast);
    flywheelB.setIdleMode(CANSparkMax.IdleMode.kCoast);

    m_pidControllerA = flywheelA.getPIDController();
    m_pidControllerB = flywheelB.getPIDController();

    m_encoder = flywheelA.getEncoder();

    // set PID coefficients
    m_pidControllerA.setP(kP);
    m_pidControllerA.setI(kI);
    m_pidControllerA.setD(kD);
    // m_pidControllerA.setIZone(kIz);
    // m_pidControllerA.setFF(kFF);
    m_pidControllerA.setOutputRange(kMinOutput, kMaxOutput);

    m_pidControllerB.setP(kP);
    m_pidControllerB.setI(kI);
    m_pidControllerB.setD(kD);
    // m_pidControllerB.setIZone(kIz);
    // m_pidControllerB.setFF(kFF);
    m_pidControllerB.setOutputRange(kMinOutput, kMaxOutput);

    if (tuning) {
      // display PID coefficients on SmartDashboard
      SmartDashboard.putNumber("P Gain Flywheel", kP);
      SmartDashboard.putNumber("I Gain Flywheel", kI);
      SmartDashboard.putNumber("D Gain Flywheel", kD);
      // SmartDashboard.putNumber("I Zone Flywheel", kIz);
      // SmartDashboard.putNumber("Feed Forward Flywheel", kFF);
      // SmartDashboard.putNumber("Max Output Flywheel", kMaxOutput);
      // SmartDashboard.putNumber("Min Output Flywheel", kMinOutput);
    }
  }

  public void setTarget(double target) {
    setPoint = target;
  }

  public double getFlywheelSetPoint() {
    return setPoint;
  }

  // takes in RPM
  public void accelerateToSetPoint() {
    // read PID coefficients from SmartDashboard
    double p, i, d;
    // double iz, ff;
    if (tuning) {
      p = SmartDashboard.getNumber("P Gain Flywheel", 0);
      i = SmartDashboard.getNumber("I Gain Flywheel", 0);
      d = SmartDashboard.getNumber("D Gain Flywheel", 0);
      // iz = SmartDashboard.getNumber("I Zone Flywheel", 0);
      // ff = SmartDashboard.getNumber("Feed Forward Flywheel", 0);
      // double max = SmartDashboard.getNumber("Max Output Flywheel", 0);
      // double min = SmartDashboard.getNumber("Min Output Flywheel", 0);

      // if PID coefficients on SmartDashboard have changed, write new values to
      // controller
      if ((p != kP)) {
        m_pidControllerA.setP(p);
        m_pidControllerB.setP(p);
        kP = p;
      }
      if ((i != kI)) {
        m_pidControllerA.setI(i);
        m_pidControllerB.setI(i);
        kI = i;
      }
      if ((d != kD)) {
        m_pidControllerA.setD(d);
        m_pidControllerB.setD(d);
        kD = d;
      }
      // if ((iz != kIz)) {
      //   m_pidControllerA.setIZone(iz);
      //   m_pidControllerB.setIZone(iz);
      //   kIz = iz;
      // }
      // if ((ff != kFF)) {
      //   m_pidControllerA.setFF(ff);
      //   m_pidControllerB.setFF(ff);
      //   kFF = ff;
      // }
      // if ((max != kMaxOutput) || (min != kMinOutput)) {
      //    m_pidControllerA.setOutputRange(min, max);
      //   m_pidControllerB.setOutputRange(min, max);
      //   kMinOutput = min;
      //   kMaxOutput = max;
      // }
    }

    // setPoint = speed * maxRPM;

    var newSetPoint = Math.min(setPoint, maxRPM) * 2;
    m_pidControllerA.setReference(newSetPoint, ControlType.kVelocity);
    m_pidControllerB.setReference(newSetPoint, ControlType.kVelocity);

    // m_pidController.setReference(setPoint, ControlType.kVelocity);

    SmartDashboard.putNumber("Flywheel SetPoint", setPoint);
    SmartDashboard.putNumber("Current Flywheel RPM", m_encoder.getVelocity());

  }

  public void coastFlywheel() {
    flywheelA.set(0);
    flywheelB.set(0);
    SmartDashboard.putNumber("Current Flywheel RPM", m_encoder.getVelocity());
  }

  public double getFlywheelSpeed() {
    return m_encoder.getVelocity();
  }

  public void setFlywheelTargetDirect(double speed) {
    speed = speed * 2;
    flywheelA.set(speed);
    flywheelB.set(speed);
  }

  public void addFlywheelSmartDashboard() {
    SmartDashboard.putNumber("Flywheel A", flywheelA.getEncoder().getVelocity());
    SmartDashboard.putNumber("Flywheel B", flywheelB.getEncoder().getVelocity());
    SmartDashboard.putNumber("Displacement A", flywheelA.getEncoder().getPosition());
    SmartDashboard.putNumber("Displacement B", flywheelB.getEncoder().getPosition());
    SmartDashboard.putNumber("Flywheel RPM", getFlywheelSpeed());
    SmartDashboard.putNumber(FLYWHEEL_TARGET, setPoint);
  }

  @Override
  public void periodic() {
    // addFlywheelSmartDashboard();
  }
}