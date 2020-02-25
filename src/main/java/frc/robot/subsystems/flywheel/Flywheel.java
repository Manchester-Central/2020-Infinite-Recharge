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
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class Flywheel extends SubsystemBase implements IFlywheel {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANPIDController m_pidControllerA, m_pidControllerB;
  private CANEncoder m_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
  CANSparkMax flywheelA, flywheelB;
  public final String FLYWHEEL_TARGET = "Flywheel target speed RPM";

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
  
    // PID coefficients
    kP = 0.0002;
    kI = 0;
    kD = 0;
    kIz = 0;
    kFF = 0;
    maxRPM = 5676;
    kMinOutput = 0;
    kMaxOutput = 1;

    // set PID coefficients
    m_pidControllerA.setP(kP);
    m_pidControllerA.setI(kI);
    m_pidControllerA.setD(kD);
    
    m_pidControllerB.setP(kP);
    m_pidControllerB.setI(kI);
    m_pidControllerB.setD(kD);


    // m_pidController.setIntegratorRange(minimumIntegral, maximumIntegral);
    // m_pidController.setIZone(kIz);

    // m_pidControllerA.setFF(kFF);
    m_pidControllerA.setOutputRange(kMinOutput, kMaxOutput);
    
    // m_pidControllerB.setFF(kFF);
    m_pidControllerB.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain Flywheel", kP);
    SmartDashboard.putNumber("I Gain Flywheel", kI);
    SmartDashboard.putNumber("D Gain Flywheel", kD);
    SmartDashboard.putNumber(FLYWHEEL_TARGET, 0);

    SmartDashboard.putNumber("I Zone Flywheel", kIz);
    SmartDashboard.putNumber("Feed Forward Flywheel", kFF);
    SmartDashboard.putNumber("Max Output Flywheel", kMaxOutput);
    SmartDashboard.putNumber("Min Output Flywheel", kMinOutput);

  }

  public void setTarget(double target) {
    SmartDashboard.putNumber(FLYWHEEL_TARGET, target);
  }

  // takes in RPM
  public void accelerateToSetPoint() {
    double setPoint = SmartDashboard.getNumber(FLYWHEEL_TARGET, 0);
    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain Flywheel", 0);
    double i = SmartDashboard.getNumber("I Gain Flywheel", 0);
    double d = SmartDashboard.getNumber("D Gain Flywheel", 0);
    
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

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
    /* if ((iz != kIz)) {
      m_pidControllerA.setIZone(iz);
      m_pidControllerB.setIZone(iz);
      kIz = iz;
    }
    if ((ff != kFF)) {
      m_pidControllerA.setFF(ff);
      m_pidControllerB.setFF(ff);
      kFF = ff;
    } */
    if ((max != kMaxOutput) || (min != kMinOutput)) {
      //m_pidControllerA.setOutputRange(min, max);
      //m_pidControllerB.setOutputRange(min, max);
      kMinOutput = min;
      kMaxOutput = max;
    }

    // setPoint = speed * maxRPM;

    setPoint = Math.min(setPoint, maxRPM) * 2;
    m_pidControllerA.setReference(setPoint, ControlType.kVelocity);
    m_pidControllerB.setReference(setPoint, ControlType.kVelocity);

    // m_pidController.setReference(setPoint, ControlType.kVelocity);

    SmartDashboard.putNumber("SetPoint", setPoint);
    SmartDashboard.putNumber("Current Flywheel RPM", m_encoder.getVelocity());

  }

  public void coastFlywheel() {
    flywheelA.set(0);
    flywheelB.set(0);
    SmartDashboard.putNumber("Current Flywheel RPM", m_encoder.getVelocity());
  }

  public double getCurrentFlywheelRPM() {
    return m_encoder.getVelocity();
  }
  

  public double getFlywheelSpeed() {
    return flywheelA.getEncoder().getVelocity();
  }

  public void setFlywheelTargetDirect(double speed) {
    speed = speed * 2;
    flywheelA.set(speed);
    flywheelB.set(speed);
  }

  /*
  public void driveWithPID() {
    double pidSpeed = m_pidController.calculate(getFlywheelSpeed());

    SmartDashboard.putNumber("Flywheel pidSpeed", pidSpeed);
    
    if (pidSpeed < 0) {
      pidSpeed = 0;
    }

    flywheelA.set(pidSpeed);
    flywheelB.set(pidSpeed);
  } */

  public void addFlywheelSmartDashboard() {
    SmartDashboard.putNumber("Flywheel A", flywheelA.getEncoder().getVelocity());
    SmartDashboard.putNumber("Flywheel B", flywheelB.getEncoder().getVelocity());
    SmartDashboard.putNumber("Displacement A", flywheelA.getEncoder().getPosition());
    SmartDashboard.putNumber("Displacement B", flywheelB.getEncoder().getPosition());

    SmartDashboard.putNumber("Flywheel RPM", getFlywheelSpeed());
  }

  @Override
  public void periodic(){
    addFlywheelSmartDashboard();
  }

}