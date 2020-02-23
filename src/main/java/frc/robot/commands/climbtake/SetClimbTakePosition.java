/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climbtake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotConstants2019;
import frc.robot.RobotConstants2020;

public class SetClimbTakePosition extends CommandBase {

  double target;
  boolean extended;

  public SetClimbTakePosition(double target, boolean extended) {
    addRequirements(Robot.climbTake);

    this.target = target;
    this.extended = extended;
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Robot.climbTake.setPivotPosition(target);

    if (extended) {
      Robot.climbTake.setExtenderPosition(RobotConstants2020.EXTENDER_OUT);
    } else {
      Robot.climbTake.setExtenderPosition(RobotConstants2020.EXTENDER_IN);
    }
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
