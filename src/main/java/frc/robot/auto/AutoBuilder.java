/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auto.commands.*;

/**
 * 
 */
public class AutoBuilder {

    Preferences prefs = Preferences.getInstance();
    SequentialCommandGroup commandList = new SequentialCommandGroup(); // sequence of commands to run

    public void init() {
        int i = 1;
        // if there is an autoX in robot preferences, keep running!!!!!
        while (prefs.containsKey("auto" + i)) {

            System.out.println(prefs.getString("auto" + i, null));
            // assigns argumnents from autoX (where x = i in iterator)
            String args = prefs.getString("auto" + i, null);
            // runs ParseCommand which separates the arguments apart (all the ? and & stuff)
            ParseCommand parsedCommand = new ParseCommand(args);
            // System.out.println(argParse.toString());

            Command command = this.getCommand(parsedCommand); // assigns current command to command
            commandList.addCommands(command); // add current command to queue of next commands
            System.out.printf("Added command %s for %d\n", command.getClass(), i);

            i++;
        }
    }

    // makes commandList public for use outside AutoBuilder
    public Command getAutoCommand() {
        return commandList;
    }

    // depending on the arguments, creates new object
    private Command getCommand(ParseCommand parsedCommand) {
        switch (parsedCommand.commandName) {
        case AutoDrive.COMMAND_NAME:
            return new AutoDrive(parsedCommand);
        case DoNothing.COMMAND_NAME:
            return new DoNothing(parsedCommand);
        case TurnAngleRobot.COMMAND_NAME:
            return new TurnAngleRobot(parsedCommand);
        case AimTurret.COMMAND_NAME:
            return new AimTurret(parsedCommand);
        case MoveToIntakePosition.COMMAND_NAME:
            //return new MoveToIntakePosition(parsedCommand);
            return new NullCommand();
        case SetCameraState.COMMAND_NAME:
            return new SetCameraState(parsedCommand);
        case AutoPrepareFlywheel.COMMAND_NAME:
            return new AutoPrepareFlywheel(parsedCommand);
        case AutoShoot.COMMAND_NAME:
            return new AutoShoot(parsedCommand);
        case SetIntakeState.COMMAND_NAME:
            return new SetIntakeState(parsedCommand);
        default:
            System.out.println("Auto command not recognized: " + parsedCommand);
            return new NullCommand();
        }
    }
}
