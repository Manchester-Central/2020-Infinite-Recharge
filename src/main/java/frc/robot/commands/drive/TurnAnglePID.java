/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TurnAnglePID extends CommandBase {
  public TurnAnglePID(double angle) {
    addRequirements(Robot.driveBase);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    targetAngle = angle;
  }

  double targetAngle;
  double targetRight;
  double targetLeft;

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.driveBase.setTargetAngle(targetAngle);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Robot.driveBase.PIDDrive();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return Robot.driveBase.isAtTarget();
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }

}
