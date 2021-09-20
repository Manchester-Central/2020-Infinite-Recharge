/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.Robot;
import frc.robot.auto.ParseCommand;
import frc.robot.commands.climbtake.SetIntake;
import frc.robot.commands.drive.PathDrive;

/**
 * Autonomously drives robot.
 */
public class AutoPathDrive extends ParallelDeadlineGroup {

    public static final String COMMAND_NAME = "path-drive";

    public AutoPathDrive(ParseCommand parsedCommand) {
        super(new PathDrive(parsedCommand.getArgument("path"), Robot.driveBase), new SetIntake(0.3));
    }
}
