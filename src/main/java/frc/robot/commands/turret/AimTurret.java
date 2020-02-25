/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.commands.util.DoneCommand;

public class AimTurret extends DoneCommand {
  public AimTurret() {
    addRequirements(Robot.turret);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  private double limelightXAngle, limelightYAngle;
  private double offsetX;

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

      limelightXAngle = Robot.camera.getXAngle();
      Robot.turret.setPanTarget(limelightXAngle + Robot.turret.getPanAngle());

      Robot.flywheel.setTarget(targetData.getSpeed());
    }
    Robot.turret.PIDDrive();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean isDone() {
    boolean tilt = Robot.turret.getTiltAngle() == Robot.turret.getTiltTarget();
    boolean pan = Robot.turret.getPanAngle() == Robot.turret.getPanTarget();

    return (tilt && pan);
    // return true; // TODO: set true when turret is aligned
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
