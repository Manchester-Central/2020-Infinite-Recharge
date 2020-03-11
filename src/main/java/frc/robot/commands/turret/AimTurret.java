/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;
import frc.robot.commands.util.DoneCommand;

public class AimTurret extends DoneCommand {
  public AimTurret() {
    addRequirements(Robot.turret);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    SmartDashboard.putNumber("Pan Trim", panTrim);
  }
  private double limelightXAngle, limelightYAngle;
  private double panTrim = -2.5;

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.turret.setPanTarget(Robot.turret.getPanAngle());
    Robot.turret.setTiltTargetAngle(Robot.turret.getTiltAngle());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    if (Robot.camera.hasTarget()) {
      limelightYAngle = Robot.camera.getYAngle();

      var targetData = Robot.flywheelTable.getIdealTarget(limelightYAngle);
      Robot.turret.setTiltTargetAngle(targetData.getAngle());

      panTrim = SmartDashboard.getNumber("Pan Trim", panTrim);

      limelightXAngle = Robot.camera.getXAngle();
      Robot.turret.setPanTarget(limelightXAngle + Robot.turret.getPanAngle() + panTrim);

      Robot.flywheel.setTarget(targetData.getSpeed());
    }
    else {
      Robot.turret.setTiltTargetAngle(RobotConstants2020.MIDDLE_HOOD_RAW);
      Robot.turret.setPanTarget(RobotConstants2020.PAN_ZERO_RAW);
    }
    Robot.turret.PIDDrive(true);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean isDone() {
    boolean tilt = Robot.turret.getTiltAngle() == Robot.turret.getTiltTarget();

    double panTarget = Robot.turret.getPanTarget();
    double panCurrent = Robot.turret.getPanAngle();
    boolean pan = ((panTarget - 2) < panCurrent) && (panCurrent < (panTarget + 2));

    return (tilt && pan) && Robot.camera.hasTarget();
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
