/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivebase;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DummyDrive extends SubsystemBase implements IDriveBase {
  /**
   * Creates a new DummyDrive.
   */
  public DummyDrive() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public SpeedControllerGroup getLeftDrive() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SpeedControllerGroup getRightDrive() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double encoderInches(CANSparkMax driveInput) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double speedToVolts(double speed) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void tankDriveVolts(double leftSpeed, double rightSpeed) {
    // TODO Auto-generated method stub

  }

  @Override
  public double angleToDist(double angle) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void reportPosition() {
    // TODO Auto-generated method stub

  }

  @Override
  public double getRightPosition() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getLeftPosition() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void PIDDrive() {
    // TODO Auto-generated method stub

  }

  @Override
  public void resetPosition() {
    // TODO Auto-generated method stub

  }

  @Override
  public PIDController getPIDLeft() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PIDController getPIDRight() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setTarget(double left, double right) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setTargetAngle(double targetAngle) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isAtTarget() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isAtRightTarget() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isAtLeftTarget() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void resetOdometry() {
    // TODO Auto-generated method stub

  }

  @Override
  public Pose2d getPose() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void tankDrive(double left, double right) {
    // TODO Auto-generated method stub

  }
}
