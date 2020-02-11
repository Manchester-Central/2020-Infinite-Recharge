/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.inputs;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ResetNavX extends CommandBase {
  public ResetNavX() {
   addRequirements(Robot.navx);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.navx.reset(); // resets all variables stored on navX (such as yaw)
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
