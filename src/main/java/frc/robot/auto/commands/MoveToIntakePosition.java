/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import frc.robot.Robot;
import frc.robot.auto.ParseCommand;

public class MoveToIntakePosition extends BaseAutoCommand {

  public static final String COMMAND_NAME = "lowerArm";

  /**
   * Creates a new MoveToIntakePosition.
   */
  public MoveToIntakePosition(ParseCommand parsedCommand) {
    super(parsedCommand);
    addRequirements(Robot.climbTake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    super.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.climbTake.goToLimit();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.climbTake.getLimitSwitchState();
  }
}
