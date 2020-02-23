/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.inputs;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Wait extends CommandBase {

  long startTime; // stores current time at init
  int timeMs; // stores target time

  public Wait(int timeMs) {
    this.timeMs = timeMs;
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return (System.currentTimeMillis() - startTime) > timeMs;
  }
}
