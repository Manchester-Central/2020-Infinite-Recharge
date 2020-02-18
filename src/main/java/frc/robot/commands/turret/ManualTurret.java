/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ManualTurret extends CommandBase {
  public ManualTurret() {
    addRequirements(Robot.turret, Robot.flywheel);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  double minAngleX;
  double maxAngleX;
  double minAngleY;
  double maxAngleY;
  double speedX, speedY;

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    minAngleX = 0;
    maxAngleX = 0;
    minAngleY = 0;
    maxAngleY = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
  
    speedX = Robot.oi.getTurretPanTarget();
    speedY = Robot.oi.getTurretHoodTarget();

    if (Robot.turret.getXPosition() < minAngleX && speedX < 0) {
      speedX = 0;
    }
    if (Robot.turret.getXPosition() > maxAngleX && speedX > 0) {
      speedX = 0;
    }
    Robot.turret.setXSpeedUnsafe(speedX);

    if (Robot.turret.getHoodAngle() < minAngleY && speedY < 0) {
      speedY = 0;
    }
    if (Robot.turret.getHoodAngle() > maxAngleY && speedY > 0) {
      speedY = 0;
    }
    Robot.turret.setYSpeedUnsafe(speedY);

    Robot.flywheel.setFlywheelTargetDirect(Robot.oi.getFlywheelTarget());
    }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean isDone() {
    return true; 
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
