/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import frc.robot.Robot;
import frc.robot.auto.ParseCommand;
import frc.robot.util.LogUtils;

/**
 * Autonomously drives robot.
 */
public class SimpleAutoDrive extends BaseAutoCommand {

    public static final String COMMAND_NAME = "simple-drive";
    private double speed = 0.3;

    public SimpleAutoDrive(ParseCommand parsedCommand) {
        super(parsedCommand);
        addRequirements(Robot.driveBase);

        var speedArgument =  parsedCommand.getArgument("speed");
        if(speedArgument != null)
        {
            speed = Double.parseDouble(speedArgument);
        }
    }

    @Override
    public void initialize() {
        super.initialize();
        LogUtils.log("Simple Auto drive init");
    }

    // sets the robot to drive at speed from arguments of parsedCommand
    @Override
    public void execute() {
        LogUtils.log("Simple Auto drive executing");
        Robot.driveBase.differentialDrive1.tankDrive(speed, speed);
    }

    public boolean isFinished() {
        return super.isFinished();
    }

    public void end(boolean interrupted) {
        log("done simple autodrive\n");
        super.end(interrupted);
        Robot.driveBase.differentialDrive1.tankDrive(0, 0);
    }
}
