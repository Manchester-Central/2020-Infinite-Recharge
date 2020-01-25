/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import javax.swing.text.StyleContext.SmallAttributeSet;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; // 0
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX; // 1
import edu.wpi.first.wpilibj.AnalogPotentiometer; // gets angle
import edu.wpi.first.wpilibj.controller.PIDController; // sets angle
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.SpeedController;
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
  private double slope, intercept, minVoltage, maxVoltage, minAngle, maxAngle, p, i, d, calibrationA, calibrationB;

  // TO-DO: Figure out values b/c they are needed to get angle of climbTake
  public ClimbTake19() {

    minVoltage = 0.1057;
    maxVoltage = 0.338;
    calibrationA = 0.0;
    calibrationB = 115.0;
    p = 0.07;
    i = 0;
    d = 0;
    minAngle = 20.0;
    maxAngle = 110.0;

    rotate0 = new WPI_TalonSRX(1);
    rotate1 = new WPI_VictorSPX(6);
    anglePot = new AnalogPotentiometer(0);
    pid = new PIDController(p, i, d);
    speedController = new SpeedControllerGroup(rotate0, rotate1);

    slope = (calibrationB - calibrationA) / (maxVoltage - minVoltage);
    intercept = calibrationB - slope * maxVoltage;

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("ClimbTake Angle", getAngle());
    /* double speed = -Robot.oi.getDriver().getRawAxis(1);
    setArmSpeed(speed); */
  }

  public void setArmSpeed(double speed) {
    if(getAngle() < minAngle && speed < 0){
      speed = 0;
    }
    if(getAngle() > maxAngle && speed > 0){
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

  // sets angle of climbtake
  public void moveArmAutomatically() {
    double offset = limelight.getYAngle();

    // while(Math.abs(offset) > 10.0) {
    // if (offset > 0) {
    // setArmPosition(0.01);
    // }
    // else if (offset < 0) {
    // setArmPosition(-0.01);
    // }
    // }

  }

}
