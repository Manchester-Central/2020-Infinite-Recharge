/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavXTurnRobot extends Command {
  public NavXTurnRobot() {
    requires(Robot.navx);
    requires(Robot.driveBase);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // get data from controller and NavX
    double currentYaw = Robot.navx.getNavYaw();
    double currentRightJoystickAngle = Robot.oi.getRobotTargetAngle();
    
    double deltaLeft = currentRightJoystickAngle - currentYaw;
    // if deltaLeft is positive, need to subtract 360 so we don't go over 360deg (or else it spazzes out :O)
    double deltaRight = deltaLeft < 0 ? deltaLeft + 360 : deltaLeft - 360;

    // if right joystick is too close to center, don't do anything
    if (!Robot.oi.shouldUseRobotTargetAngle()) {
      double speed = Robot.oi.getSpeedDuringNavX();
      Robot.driveBase.differentialDrive1.tankDrive(speed, speed);
      return;
    }

    // calculates the shortest angular displacement to goal
    double delta = deltaRight;
    if (Math.abs(deltaLeft) < Math.abs(deltaRight)) {
      delta = deltaLeft;
    }

    // visualize data
    SmartDashboard.putNumber("Joy Delta", delta);
    SmartDashboard.putNumber("Right Angle", currentRightJoystickAngle);
    SmartDashboard.putNumber("Calc Yaw", currentYaw);
    Robot.driveBase.setTargetAngle(delta);

    // tells robot to drive
    Robot.driveBase.PIDDrive();


  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.driveBase.isAtTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
