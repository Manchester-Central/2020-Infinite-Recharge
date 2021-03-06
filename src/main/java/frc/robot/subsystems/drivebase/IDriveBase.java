// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems.drivebase;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 *
 */
public interface IDriveBase extends Subsystem {

    public void arcadeDrive(double xaxisSpeed, double zaxisRotate, boolean smoothing);

    public void tankDrive(double left, double right);

    public SpeedControllerGroup getLeftDrive();

    public SpeedControllerGroup getRightDrive();

    public double encoderInches(CANSparkMax driveInput);

    public double speedToVolts(double speed);

    public void tankDriveVolts(double leftSpeed, double rightSpeed);

    public double angleToDist(double angle);

    public void reportPosition();

    public double getRightPosition();

    public double getLeftPosition();

    public void PIDDrive();

    public void resetPosition();

    public PIDController getPIDLeft();

    public PIDController getPIDRight();

    public void setTarget(double left, double right);

    public void setTargetAngle(double targetAngle);

    public boolean isAtTarget();

    public boolean isAtRightTarget();

    public boolean isAtLeftTarget();

    public void resetOdometry(Pose2d resetPosition);

    public void resetOdometry();

    public Pose2d getPose();

    public DifferentialDriveWheelSpeeds getWheelSpeeds();

    public void periodic();

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
