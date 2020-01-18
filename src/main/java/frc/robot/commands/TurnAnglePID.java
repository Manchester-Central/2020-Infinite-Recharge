/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnAnglePID extends Command {
  public TurnAnglePID(double angle) {
    requires(Robot.driveBase);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    targetAngle = angle;
  }

  double targetAngle;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double delta = Robot.driveBase.angleToDist(targetAngle);
    double targetLeft = Robot.driveBase.getLeftPosition() + delta;
    double targetRight = Robot.driveBase.getRightPosition() - delta;
    Robot.driveBase.setTarget(targetLeft, targetRight);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveBase.PIDDrive();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
