/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;

import javax.swing.text.StyleContext.SmallAttributeSet;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

/**
 * Add your docs here.
 */
public class Turret extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Turret(Robot.RobotType robotType) {
    xP = 0;
    xI = 0;
    xD = 0;
    yP = 0;
    yI = 0;
    yD = 0;
    pidX = new PIDController(xP, xI, xD);
    pidY = new PIDController(yP, yI, yD);

    slopeX = RobotConstants2020.ANGLE_POT_SLOPE_X; // TODO: CHANGE!!!
    interceptX = RobotConstants2020.ANGLE_POT_INTERCEPT_X; // TODO: CHANGE!!!
    slopeY = RobotConstants2020.ANGLE_POT_SLOPE_Y; // TODO: CHANGE!!!
    interceptY = RobotConstants2020.ANGLE_POT_INTERCEPT_Y; // TODO: CHANGE!!!

    int channelX = RobotConstants2020.ANGLE_POT_X; // TODO: CHANGE!!!!!!!!!!!!!!!!!!!!
    int channelY = RobotConstants2020.ANGLE_POT_Y; // TODO: CHANGE!!!!!!!!!!!!!!!!!!!!

    anglePotX = new AnalogPotentiometer(channelX, slopeX, interceptX);
    anglePotY = new AnalogPotentiometer(channelY, slopeY, interceptY);

    speedControllerX = new WPI_TalonSRX(RobotConstants2020.TURRET_PAN);
    speedControllerY = new WPI_TalonSRX(RobotConstants2020.TURRET_HOOD);

    minAngleX = RobotConstants2020.MIN_ANGLE_TURRET_PAN;
    maxAngleX = RobotConstants2020.MAX_ANGLE_TURRET_PAN;
    minAngleY = RobotConstants2020.MIN_ANGLE_TURRET_HOOD;
    maxAngleY = RobotConstants2020.MAX_ANGLE_TURRET_HOOD;
  }

  private double xP, xI, xD, yP, yI, yD;
  private double minAngleX, minAngleY, maxAngleX, maxAngleY;
  private double slopeX, slopeY, interceptX, interceptY;
  PIDController pidX, pidY;
  AnalogPotentiometer anglePotX, anglePotY;
  WPI_TalonSRX speedControllerX, speedControllerY;

  private double turretXDegrees(CANSparkMax input) {
    double gearRatio = (double) 10 / 1; // ratio of the axel the turntable lies on to the axel the encoder reads
    int ticksPerRev = 42; // amount of ticks in one revolution of the encoder axel
    double counts = input.getEncoder().getPosition();
    return (counts * gearRatio) / ticksPerRev;
  }

  public double getXPosition() {
    return anglePotX.get(); // make sure the slope and intercept are accounted for
  }

  public void setXTarget(double target) {
    pidX.setSetpoint(target);
  }

  private void setXSpeed(double speed) {
    if (getXPosition() < minAngleX && speed < 0) {
      speed = 0;
    }
    if (getXPosition() > maxAngleX && speed > 0) {
      speed = 0;
    }
    speedControllerX.set(speed);
  }

  public void setXSpeedUnsafe(double speed) {
    speedControllerX.set(speed); // DANGER!!
  }

  public void setYSpeedUnsafe(double speed) {
    speedControllerY.set(speed); // DANGER!!
  }

  private void PIDDriveX() {
    double maxSpeed = 0.4;
    double speed = pidX.calculate(getXPosition());
    setXSpeed(speed * maxSpeed);
    System.out.println("Turret pan angle: " + getXPosition() + " pan speed: " + speed);
  }

  public void setHoodSpeed(double speed) {
    if (getHoodAngle() < minAngleY && speed < 0) {
      speed = 0;
    }
    if (getHoodAngle() > maxAngleY && speed > 0) {
      speed = 0;
    }
    speedControllerY.set(speed);
  }

  public double getHoodAngle() {
    return anglePotY.get();
  }

  public void setHoodTargetAngle(double angle) {
    double mathAngle = angle;
    pidY.setSetpoint(mathAngle);
  }

  public void PIDDriveY() {
    double maxSpeed = 0.4;
    double speed = pidY.calculate(getHoodAngle());
    setHoodSpeed(speed * maxSpeed);
    System.out.println("Hood angle: " + getHoodAngle() + " hood speed: " + speed);
  }

  public void addTurretSmartDashboard(){
    SmartDashboard.putNumber("Y Potentiometer", getHoodAngle());
    SmartDashboard.putNumber("X Potentiometer", getXPosition());
  }

  public void PIDDrive() {
    PIDDriveX();
    PIDDriveY();
  }
}
