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

  private PIDController m_pidController;
  private CANEncoder m_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

  public Flywheel() {

    flywheelA = new CANSparkMax(RobotConstants2020.FLYWHEELA_SPARKMAX, CANSparkMax.MotorType.kBrushless);
    flywheelB = new CANSparkMax(RobotConstants2020.FLYWHEELB_SPARKMAX, CANSparkMax.MotorType.kBrushless);
    // TODO: assign in RobotConstants2020
    flywheelA.setInverted(false);
    flywheelB.setInverted(true);
    

    m_pidController = new PIDController(kP, kI, kD);
  
    // PID coefficients
    kP = 0.00005;
    kI = 0.000001;
    kD = 0;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 0.25;
    kMinOutput = -0.25;
    maxRPM = 5676;

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);

    // m_pidController.setIntegratorRange(minimumIntegral, maximumIntegral);
    // m_pidController.setIZone(kIz);

    // m_pidController.setFF(kFF);
    // m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain Flywheel", kP);
    SmartDashboard.putNumber("I Gain Flywheel", kI);
    SmartDashboard.putNumber("D Gain Flywheel", kD);
    // SmartDashboard.putNumber("I Zone Flywheel", kIz);
    // SmartDashboard.putNumber("Feed Forward Flywheel", kFF);
    // SmartDashboard.putNumber("Max Output Flywheel", kMaxOutput);
    // SmartDashboard.putNumber("Min Output Flywheel", kMinOutput);

  }

  CANSparkMax flywheelA, flywheelB;

  // takes in RPM
  public void setTargetSpeed(double setPoint) {
    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);

    /*
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
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

    // setPoint = speed * maxRPM;

    setPoint = Math.min(setPoint, maxRPM);
    m_pidController.setSetpoint(setPoint);

    // m_pidController.setReference(setPoint, ControlType.kVelocity);

    SmartDashboard.putNumber("SetPoint", setPoint);
    SmartDashboard.putNumber("ProcessVariable", m_encoder.getVelocity());
    SmartDashboard.putNumber("Flywheel current speed", getFlywheelSpeed());

  }

  

  public double getFlywheelSpeed() {
    return flywheelA.getEncoder().getPosition();
  }

  public void setFlywheelTargetDirect(double speed) {
    if (Math.abs(speed) > 0.1) {
      System.out.println("Flywheel Speed: " + speed);
    }
    flywheelA.set(speed);
    flywheelB.set(speed);
  }

  public void driveWithPID() {
    double pidSpeed = m_pidController.calculate(getFlywheelSpeed());

    if (pidSpeed > 0.5) {
      pidSpeed = 0.5;
    } else if (pidSpeed < 0) {
      pidSpeed = 0;
    }

    flywheelA.set(pidSpeed);
    flywheelB.set(pidSpeed);
  }

  public void setFlywheelTargetDashboard() {
    setTargetSpeed(SmartDashboard.getNumber("Flywheel target speed RPM", 0));
    driveWithPID();
  }

  public void addFlywheelSmartDashboard() {
    SmartDashboard.putNumber("Flywheel A", flywheelA.getEncoder().getPosition());
    SmartDashboard.putNumber("Flywheel B", flywheelB.getEncoder().getPosition());
  }

  @Override
  public void periodic(){
    addFlywheelSmartDashboard();
  }

}