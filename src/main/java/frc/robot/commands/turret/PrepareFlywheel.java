/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.util.DoneCommand;
import frc.robot.util.LogUtils;

public class PrepareFlywheel extends DoneCommand {
  public PrepareFlywheel() {
    addRequirements(Robot.flywheel);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  double currentFlywheelRPM, targetFlywheelRPM;

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    currentFlywheelRPM = Robot.flywheel.getFlywheelSpeed();
    targetFlywheelRPM = Robot.flywheel.getFlywheelSetPoint();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

    currentFlywheelRPM = Robot.flywheel.getFlywheelSpeed();
    targetFlywheelRPM = Robot.flywheel.getFlywheelSetPoint();
    Robot.flywheel.accelerateToSetPoint();

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean isDone() {
    boolean result = (((targetFlywheelRPM * 0.9) < currentFlywheelRPM) && (currentFlywheelRPM < (targetFlywheelRPM * 1.1)));
    LogUtils.log("PrepareFlywheel is done: isDone? = " + result);
    return result;
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
