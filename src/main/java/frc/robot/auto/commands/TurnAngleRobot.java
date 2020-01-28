/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import frc.robot.auto.ParseCommand;

public class TurnAngleRobot extends BaseAutoCommand {

  double angle;

  public TurnAngleRobot(ParseCommand parsedCommand) {

    super(parsedCommand);
    this.angle = Double.parseDouble(parsedCommand.getArgument("robotAngle"));

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // TODO: create TurnAnglePID object + do the distance auto stuff later (1/27/20)
  }

}
