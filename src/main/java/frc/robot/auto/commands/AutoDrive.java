/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import frc.robot.Robot;
import frc.robot.auto.ParseCommand;

/**
 * Autonomously drives robot.
 */
public class AutoDrive extends BaseAutoCommand {

    public static final String COMMAND_NAME = "drive";
    // from parsedCommand, a double distance will be assigned to driveBase
    double distance;

    public AutoDrive(ParseCommand parsedCommand) {
        super(parsedCommand);
        requires(Robot.driveBase);

        this.distance = Double.parseDouble(parsedCommand.getArgument("distanceIn"));
    }

    @Override
    protected void initialize() {
        Robot.driveBase.setTarget(distance, distance);
    }

    // sets the robot to drive at speed from arguments of parsedCommand
    @Override
    protected void execute() {
        Robot.driveBase.PIDDrive();
    }

}
