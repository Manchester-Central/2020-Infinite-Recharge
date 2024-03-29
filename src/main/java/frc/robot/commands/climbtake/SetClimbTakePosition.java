/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climbtake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class SetClimbTakePosition extends CommandBase {

  double target;
  double position;

  public SetClimbTakePosition(double target, double position) {
    addRequirements(Robot.climbTake);

    this.target = target;
    this.position = position;
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.climbTake.setPivotPosition(target);
    // Robot.climbTake.setExtenderPosition(position);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Robot.climbTake.PIDDrivePivot();
    //if (Robot.oi.getEnableExtend()) {
      Robot.climbTake.setExtenderSpeed(Robot.oi.manualExtend());
    //}
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
