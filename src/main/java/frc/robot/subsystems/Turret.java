/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.Robot;
import com.revrobotics.CANSparkMax;

/**
 * Add your docs here.
 */
public class Turret extends Subsystem {
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

    slope = 1; // TODO: CHANGE!!!
    intercept = 1; // TODO: CHANGE!!!
  }

  private double xP, xI, xD, yP, yI, yD;
  private double minAngle, maxAngle;
  private double slope, intercept;
  PIDController pidX, pidY;
  AnalogPotentiometer anglePot;
  SpeedControllerGroup speedController;

  private double turretXDegrees(CANSparkMax input) {
    double gearRatio = (double) 10 / 1; // ratio of the axel the turntable lies on to the axel the encoder reads
    int ticksPerRev = 42; // amount of ticks in one revolution of the encoder axel
    double counts = input.getEncoder().getPosition();
    return (counts * gearRatio) / ticksPerRev;
  }

  public double getXPosition() {
    return 0; // TODO: CHANGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  }

  public void setXTarget() {

  }

  private void PIDDriveX() {

  }

  public void setHoodSpeed(double speed) {
    if (getHoodAngle() < minAngle && speed < 0) {
      speed = 0;
    }
    if (getHoodAngle() > maxAngle && speed > 0) {
      speed = 0;
    }
    speedController.set(speed);
  }

  public double getHoodAngle() {
    return slope * anglePot.get() + intercept;
  }

  public void setHoodTargetAngle(double angle) {
    double mathAngle = angle;
    pidY.setSetpoint(mathAngle);
  }

  public void PIDDriveY() {
    double maxSpeed = 0.4;
    double speed = pidY.calculate(getHoodAngle());
    setHoodSpeed(speed * maxSpeed);
    System.out.println("Angle: " + getHoodAngle() + " Speed: " + speed);
  }

  public void PIDDrive() {
    PIDDriveX();
    PIDDriveY();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
