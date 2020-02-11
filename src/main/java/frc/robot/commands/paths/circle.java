/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.paths;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.Robot;

public class circle extends Command {
  public circle() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    ks = 0.593;
    kv = 0.172;
    ka = 0.0393;
    rsquared = 0.987;
    trackWidth = 117.072;
    maxVoltage = 10; // TODO: probably move to a constant file or something

    var feedForward = new SimpleMotorFeedforward(ks, kv, ka);
    var kinematics = new DifferentialDriveKinematics(trackWidth);

    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(feedForward, kinematics, maxVoltage);
    var config = new TrajectoryConfig(kv, ka);
    config.setKinematics(kinematics);
    config.addConstraint(autoVoltageConstraint);
    Trajectory trajectory;

    String jsonPath = "PathWeaverTest.wpilib.json";

    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(jsonPath);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open Trajectory", ex.getStackTrace());
    }

    RamseteCommand ramseteCommand = new RamseteCommand(trajectory, Robot.driveBase::getPose,
        new RamseteController(2, 0.7), feedForward, kinematics, Robot.driveBase::getWheelSpeeds,
        Robot.driveBase.getLeftPID(), Robot.driveBase.getRightPID(), Robot.driveBase::tankDriveVolts, Robot.driveBase);
  }

  private double maxVoltage;
  private double ks;
  private double kv;
  private double ka;
  private double rsquared;
  private double trackWidth;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
