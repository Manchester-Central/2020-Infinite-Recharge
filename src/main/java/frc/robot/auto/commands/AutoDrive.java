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
        addRequirements(Robot.driveBase);

        this.distance = Double.parseDouble(parsedCommand.getArgument("distanceIn"));
    }

    @Override
    public void initialize() {
        super.initialize();
        double targetL = Robot.driveBase.getLeftPosition() + distance;
        double targetR = Robot.driveBase.getRightPosition() + distance;
        Robot.driveBase.setTarget(targetL, targetR);
    }

    // sets the robot to drive at speed from arguments of parsedCommand
    @Override
    public void execute() {
        Robot.driveBase.PIDDrive();
    }

    public boolean isFinished() {
        if (Robot.driveBase.isAtTarget()) {
            log("Autodrive at target\n");
            return true;
        }
        return super.isFinished();
    }

    public void end(boolean interrupted) {
        log("done autodrive\n");
        super.end(interrupted);
    }
}
