/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.serializer;

import frc.robot.Robot;
import frc.robot.commands.util.DoneCommand;
import frc.robot.subsystems.serializer.SerializerSpeed;

public class SerializerFeed extends DoneCommand {
  public SerializerFeed() {
    // Use requires() here to declare subsystem dependencies
    addRequirements(Robot.serializer);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Robot.serializer.driveTurnTable(SerializerSpeed.fast);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isDone() {
    return false;
  }
}
