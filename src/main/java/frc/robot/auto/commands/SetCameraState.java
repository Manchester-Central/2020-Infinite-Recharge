/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import frc.robot.Robot;
import frc.robot.auto.ParseCommand;
import frc.robot.util.LogUtils;

public class SetCameraState extends BaseAutoCommand {
  /**
   * Creates a new SetCameraState.
   */

  double pipeline;

  public static final String COMMAND_NAME = "setCamera";

  public SetCameraState(ParseCommand parseCommand) {
    super(parseCommand);
    addRequirements(Robot.camera);

    this.pipeline = Double.parseDouble(parseCommand.getArgument("pipeline"));
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    super.initialize();
    LogUtils.log("Camera state is set");
    Robot.camera.setPipeline(pipeline);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
