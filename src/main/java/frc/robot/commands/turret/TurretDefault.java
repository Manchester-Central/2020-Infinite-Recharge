/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;

public class TurretDefault extends CommandBase {
  public TurretDefault() {
    addRequirements(Robot.turret);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  double panSpeedScale;

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.turret.setTiltTargetAngle(RobotConstants2020.MIN_HOOD_RAW + 5);
    panSpeedScale = 0.30;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Robot.turret.setPanSpeed(Robot.oi.getTurretPanTarget() * panSpeedScale);

    Robot.turret.PIDDriveTilt();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean isDone() {
    return Robot.turret.getTiltAngle() == Robot.turret.getTiltTarget();
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
