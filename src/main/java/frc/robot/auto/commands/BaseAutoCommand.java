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
 * Add your docs here.
 */
public abstract class BaseAutoCommand extends Command {

    List<IAutoCondition> conditions = new ArrayList<IAutoCondition>();

    public BaseAutoCommand(ParseCommand parsedCommand) {
        String timeMsString = parsedCommand.getArgument("timeMs");
        String distanceInString = parsedCommand.getArgument("distanceIn");

        if (timeMsString != null) {
            int timeMs = Integer.parseInt(timeMsString);
            conditions.add(new TimeAutoCondition(timeMs));
        }

        if (distanceInString != null) {
            Double distanceIn = Double.parseDouble(distanceInString);
            conditions.add(new DistanceAutoCondition(distanceIn));
        }
    }

    @Override
    protected void initialize() {
        for (IAutoCondition condition : conditions) {
            condition.init();
        }

    }

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
