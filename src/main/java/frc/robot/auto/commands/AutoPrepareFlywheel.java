/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.auto.ParseCommand;
import frc.robot.util.LogUtils;

public class AutoPrepareFlywheel extends BaseAutoCommand {

  public static final String COMMAND_NAME = "prepareFlywheel";
  double currentFlywheelRPM, targetFlywheelRPM;

  /**
   * Creates a new AutoPrepareFlywheel.
   */
  public AutoPrepareFlywheel(ParseCommand parsedCommand) {
    super(parsedCommand);
    addRequirements(Robot.flywheel);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    super.initialize();
    LogUtils.log("AutoPrepareFlywheel init");
    currentFlywheelRPM = Robot.flywheel.getCurrentFlywheelRPM();
    targetFlywheelRPM = Robot.flywheel.getFlywheelSetPoint();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    LogUtils.log("AutoPrepareFlywheel executing");
    currentFlywheelRPM = Robot.flywheel.getCurrentFlywheelRPM();
    targetFlywheelRPM = Robot.flywheel.getFlywheelSetPoint();
    Robot.flywheel.accelerateToSetPoint();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    LogUtils.log("AutoPrepareFlywheel ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (super.isFinished()) {
      return true;
    }
    return (((targetFlywheelRPM * 0.9) < currentFlywheelRPM) && (currentFlywheelRPM < (targetFlywheelRPM * 1.1)));
  }
}
