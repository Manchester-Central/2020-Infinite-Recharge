/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.auto.ParseCommand;
import frc.robot.auto.conditions.*;

/**
 * Holds conditions that will be run.
 */
public abstract class BaseAutoCommand extends Command {

    // a list (advanced array) of called conditions
    // the conditions in this list will depend on the parameters set in "value" of SmartDashboard
    List<IAutoCondition> conditions = new ArrayList<IAutoCondition>();

    public BaseAutoCommand(ParseCommand parsedCommand) {
        // set strings to value of parameter (can be null if does not exist)
        String timeMsString = parsedCommand.getArgument("timeMs");
        String pidPosition = parsedCommand.getArgument("pidInPosition");

        // if there is a time parameter, add it to conditions to process
        if (timeMsString != null) {
            int timeMs = Integer.parseInt(timeMsString);
            conditions.add(new TimeAutoCondition(timeMs));
        }

        // if there is a PIDPosition condition, check if PID is in position
        if (pidPosition != null) {
            conditions.add(new InDrivePositionCondition());
        }
    }

    // for each condition in the list of conditions, initialize them.
    @Override
    protected void initialize() {
        for (IAutoCondition condition : conditions) {
            condition.init();
        }

    }

    // if there are no more conditions, stop autonomous.
    // if all isDone() methods from called "conditions" are true, stop auto
    @Override
    protected boolean isFinished() {

        if (conditions.size() == 0) {
            return true;
        }

        for (IAutoCondition condition : conditions) {
            if (condition.isDone()) {
                return true;
            }
        }
        return false;

    }
}
