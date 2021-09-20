/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.auto.ParseCommand;
import frc.robot.commands.serializer.SerializerFeed;
import frc.robot.commands.serializer.SerializerStop;
import frc.robot.commands.turret.PrepareFlywheel;
import frc.robot.commands.turret.SetThroatSpeed;
import frc.robot.commands.util.Deadline;
import frc.robot.subsystems.serializer.SerializerSpeed;

public class AutoShoot extends SequentialCommandGroup {
  /**
   * Creates a new AutoShoot.
   */
  public static final String COMMAND_NAME = "shoot";

  public AutoShoot(ParseCommand parsedCommand) {

    var prepareFlywheel = Deadline.createDeadline(new PrepareFlywheel(), new SerializerStop()); // finished when turret aligned + flywheel up to speed
    addCommands(prepareFlywheel);

    var prepareEjector = Deadline.createDeadline(new SetThroatSpeed(true), new PrepareFlywheel(), new SerializerStop());
    addCommands(prepareEjector);

    // aimTurret + flyWheel done (not finished) immediately, serializer never finishes (while held will interrupt)
    var shoot = Deadline.createDeadline(new SetThroatSpeed(true), new PrepareFlywheel(), new SerializerFeed(1.2, SerializerSpeed.slow)); 
    addCommands(shoot);
    addCommands(new InstantCommand(() -> Robot.serializer.manualSpeed(false), Robot.serializer));
    addCommands(new InstantCommand(() -> Robot.flywheel.coastFlywheel(), Robot.flywheel));

    // Use addRequirements() here to declare subsystem dependencies.
  }
}
