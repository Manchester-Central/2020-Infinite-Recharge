/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; 
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController; 
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants2019;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * 2 speed controllers + potentiometer Make PID that takes arm angle from
 * potentiometer + angle from limelight to adjust
 */
public class ClimbTake19 extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX rotate0;
  WPI_VictorSPX rotate1;
  Camera limelight;
  AnalogPotentiometer anglePot;
  PIDController pid;
  SpeedControllerGroup speedController;
  private double slope, intercept;
  private double minAngle, maxAngle;
  private double p, i, d;

  public ClimbTake19() {
    p = 0.07;
    i = 0;
    d = 0;
    pid = new PIDController(p, i, d);

    minAngle = 20.0;
    maxAngle = 110.0;

    rotate0 = new WPI_TalonSRX(RobotConstants2019.CLIMBTAKE_ANGLE_TALON);
    rotate1 = new WPI_VictorSPX(RobotConstants2019.CLIMBTAKE_ANGLE_VICTOR);
    speedController = new SpeedControllerGroup(rotate0, rotate1);

    double aVoltage = RobotConstants2019.CLIMBTAKE_CALIBRATION_A_VOLTAGE;
    double bVoltage = RobotConstants2019.CLIMBTAKE_CALIBRATION_B_VOLTAGE;
    double aAngle = RobotConstants2019.CLIMBTAKE_CALIBRATION_A_ANGLE;
    double bAngle = RobotConstants2019.CLIMBTAKE_CALIBRATION_B_ANGLE;
    slope = (bAngle - aAngle) / (bVoltage - aVoltage);
    intercept = bAngle - slope * bVoltage;
    anglePot = new AnalogPotentiometer(RobotConstants2019.CLIMBTAKE_ANGLE_POT);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("ClimbTake Angle", getAngle());
  }

  public void setArmSpeed(double speed) {
    if (getAngle() < minAngle && speed < 0) {
      speed = 0;
    }
    if (getAngle() > maxAngle && speed > 0) {
      speed = 0;
    }
    speedController.set(speed);
  }

  public double getAngle() {
    return slope * anglePot.get() + intercept;
  }

  public void setTargetAngle(double angle) {
    pid.setSetpoint(angle);
  }

  public void PIDDrive() {
    double maxSpeed = 0.4;
    double speed = pid.calculate(getAngle());
    setArmSpeed(speed * maxSpeed);
    System.out.println("Angle: " + getAngle() + " Speed: " + speed);
  }
}
