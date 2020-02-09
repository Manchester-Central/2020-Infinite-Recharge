/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import java.util.List;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.RunAfterReadyCommand;

public class ParallelGroup extends CommandGroup {
  /**
   * ParallelGroup creates a list of commands to check if ready/finished
   * if all commands ready, entire commandGroup is finished
   */

   Command[] commandList; // list of commands to be read

  public ParallelGroup(Command ... commandList) {
    this.commandList = commandList; // stores for local use
    for (Command command : commandList) {
      addParallel(command); // adds all commands to commandList
    }
  }

  @Override
  protected void initialize() {
    super.initialize();
    this.start();
  }

  @Override
  public boolean isFinished() {
    for (Command command : commandList) {

      boolean isDone = !command.isRunning(); // chks if command is done

      if (command instanceof RunAfterReadyCommand) { // if command is a type of RunAfterReadyCommand
        RunAfterReadyCommand runCommand = (RunAfterReadyCommand) command; // cast to RunAfterCommand

        isDone |= runCommand.isReady(); // bc of cast, can run isReady() specific command
        // |= , if isCompleted or isReady, then isDone is true

      }

      if (!isDone) { // if a command isn't done
        return false;
      }
    }
    return true;
  }
}
