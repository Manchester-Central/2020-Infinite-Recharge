/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveRunAfterReady extends RunAfterReadyCommand {
  public DriveRunAfterReady() {
    requires(Robot.driveBase);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveBase.differentialDrive1.tankDrive(0.2, 0.2);
  }

  @Override
  public boolean isReady() {
    return true;
  }


}
