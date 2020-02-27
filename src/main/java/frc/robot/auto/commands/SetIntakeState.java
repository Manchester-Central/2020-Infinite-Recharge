/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.auto.ParseCommand;

public class SetIntakeState extends BaseAutoCommand {
  /**
   * Creates a new SetIntakeState.
   */
  public static final String COMMAND_NAME = "setIntake";
  double speed;

  public SetIntakeState(ParseCommand parsedCommand) {
    super(parsedCommand);
    addRequirements(Robot.intake);

    this.speed = Double.parseDouble(parsedCommand.getArgument("speed"));
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    super.initialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.intake.setSpeedIntake(speed);
  }
}
