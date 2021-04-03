// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.drivebase.IDriveBase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PathDrive extends SequentialCommandGroup {

private final double kRamseteB = 2;
private final double kRamseteZeta = 0.7;
private final double ksVolts = 0.215;
private final double kvVoltSecondsPerMeter = 1.35;
private final double kaVoltSecondsSquaredPerMeter = 0.191;
private final double kTrackWidthMeter = 0.6731;

private final String m_pathName; 
  /** Creates a new PathDrive. */
  public PathDrive(String pathName, IDriveBase driveBase) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    m_pathName = pathName;
    addRequirements(driveBase);
    String trajectoryJSON = "output/" + pathName + ".wpilib.json";
    Trajectory trajectory; 
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory" + trajectoryJSON, ex.getStackTrace());
      return;
    }
    
    final Pose2d initialPose = trajectory.getInitialPose();
    var startCommand = new InstantCommand(() -> driveBase.resetOdometry(initialPose), driveBase);

    var driveCommand = new RamseteCommand(
      trajectory, 
      driveBase::getPose, 
      new RamseteController(kRamseteB, kRamseteZeta),  
      new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter), 
      new DifferentialDriveKinematics(kTrackWidthMeter), 
      driveBase::getWheelSpeeds, 
      new PIDController(0.055, 0, 0), //driveBase.getPIDLeft(),
      new PIDController(0.055, 0, 0), //driveBase.getPIDRight(), 
      driveBase::tankDriveVolts, 
      driveBase);

      var endCommand = new InstantCommand(() -> driveBase.tankDriveVolts(0.0, 0.0), driveBase);


    addCommands(startCommand, driveCommand, endCommand);
  }

  @Override
  public void initialize() {
    // TODO Auto-generated method stub
    super.initialize();

    System.out.println("Starting path " + m_pathName); 
  }
}
