/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import frc.robot.Robot;
import frc.robot.auto.ParseCommand;
import frc.robot.subsystems.serializer.SerializerSpeed;

public class NewAutoScore extends BaseAutoCommand {
  /**
   * Creates a new AutoShoot.
   */
  public static final String COMMAND_NAME = "autoScore";

  public NewAutoScore(ParseCommand parsedCommand) {
    super(parsedCommand);
    addRequirements(Robot.flywheel, Robot.serializer, Robot.throat);
  }

  @Override
  public void execute() {
    Robot.flywheel.accelerateToSetPoint();

    var targetFlywheelRPM = Robot.flywheel.getFlywheelSetPoint();
    var currentFlywheelRPM = Robot.flywheel.getCurrentFlywheelRPM();
    var isFlywheelAtSpeed = ((targetFlywheelRPM * 0.9) < currentFlywheelRPM) && (currentFlywheelRPM < (targetFlywheelRPM * 1.1));

    if (isFlywheelAtSpeed) {
      Robot.throat.ejectorSpeed(true);
      var throatSpeed = Robot.throat.getThroatSpeed();
      if (throatSpeed > 600) {
        Robot.serializer.driveTurnTable(SerializerSpeed.fast);
      } else {
        Robot.serializer.manualSpeed(false);
      }
    } else {
      Robot.serializer.manualSpeed(false);
      Robot.throat.ejectorSpeed(false);
    }
  }
}
