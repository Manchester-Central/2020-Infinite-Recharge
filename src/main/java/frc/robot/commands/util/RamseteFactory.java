/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.util;

import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class RamseteFactory {

    private final double KS = 0.593;
    private final double KV = 0.172;
    private final double KA = 0.0393;
    // private final double RSQUARED = 0.987;
    private final double TRACK_WIDTH = 117.072;
    private final double MAX_VOLTAGE = 10;

    private RamseteCommand makeRamseteCommand(String jsonPath) {

        var feedForward = new SimpleMotorFeedforward(KS, KV, KA);
        var kinematics = new DifferentialDriveKinematics(TRACK_WIDTH);

        var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(feedForward, kinematics, MAX_VOLTAGE);
        var config = new TrajectoryConfig(KV, KA);
        config.setKinematics(kinematics);
        config.addConstraint(autoVoltageConstraint);

        try {
            var trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(jsonPath);
            var trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);

            return new RamseteCommand(trajectory, Robot.driveBase::getPose, new RamseteController(2, 0.7), feedForward,
                    kinematics, Robot.driveBase::getWheelSpeeds, Robot.driveBase.getPIDLeft(),
                    Robot.driveBase.getPIDRight(), Robot.driveBase::tankDriveVolts, Robot.driveBase);

        } catch (IOException ex) {
            DriverStation.reportError("Unable to open Trajectory", ex.getStackTrace());

        }
        return null;
    }

    public RamseteCommand getCircleCommand() {
        return makeRamseteCommand("PathWeaverTest.wpilib.json");
    }
}
