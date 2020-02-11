/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import frc.robot.auto.ParseCommand;
import frc.robot.commands.TurnAnglePID;
import frc.robot.Robot;
import frc.robot.Robot.*;

public class TurnAngleRobot extends BaseAutoCommand {
  
  public static final String COMMAND_NAME = "turnRobot";
  double angle;

  public TurnAngleRobot(ParseCommand parsedCommand) {

    super(parsedCommand);
    this.angle = Double.parseDouble(parsedCommand.getArgument("robotAngle"));

  }

  @Override
  protected void initialize() {
    Robot.driveBase.setTargetAngle(angle);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveBase.PIDDrive();
  }

}
