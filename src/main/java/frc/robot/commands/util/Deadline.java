/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.util;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Deadline extends CommandBase {
  public Deadline(DoneCommand ... commandList) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    commands = new ArrayList<DoneCommand>();
    for (DoneCommand command : commandList) {
      commands.add(command);
    }
  }

  ArrayList<DoneCommand> commands;

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    // calls all commands in array
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    for (DoneCommand command : commands) {
      if (!command.isDone()){
        return false;
      }
    }
    return true;
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
