/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climbtake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class SetExtensionPosition extends CommandBase {
  public SetExtensionPosition() {
    addRequirements(Robot.climbTake, Robot.flywheel, Robot.turret);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.flywheel.setFlywheelTargetDirect(0);
    Robot.turret.setHoodSpeed(0);
    Robot.turret.setXSpeed(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Robot.climbTake.setExtensionPositionUNSAFE(Robot.oi.getExtensionTarget());
    Robot.climbTake.setPivotPositionUNSAFE(Robot.oi.getPivotTarget());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
