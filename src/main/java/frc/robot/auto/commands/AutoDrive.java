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

    // from parsedCommand, a double speed will be assigned to driveBase
    double speed;

    public AutoDrive(ParseCommand parsedCommand) {
        super(parsedCommand);
        requires(Robot.driveBase);

        this.speed = Double.parseDouble(parsedCommand.getArgument("speed"));
    }

    // sets the robot to drive at speed from arguments of parsedCommand
    @Override
    protected void execute() {
        Robot.driveBase.differentialDrive1.tankDrive(speed, speed);
    }

}
