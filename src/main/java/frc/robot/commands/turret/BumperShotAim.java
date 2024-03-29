/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class BumperShotAim extends CommandBase {
  public BumperShotAim() {
    addRequirements(Robot.turret);
  }

  private final double FLYWHEEL_SPEED = 4500;
  private final double TURRET_TILT = 74;

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.turret.setTiltTargetAngle(TURRET_TILT);
    Robot.turret.setPanTarget(-180);
    Robot.flywheel.setTarget(FLYWHEEL_SPEED);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Robot.turret.PIDDrive(false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrputed) {
  }

}
