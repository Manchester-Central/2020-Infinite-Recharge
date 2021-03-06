/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.serializer.SerializerFeed;
import frc.robot.commands.serializer.SerializerStop;
import frc.robot.commands.util.Deadline;

public class AimAndShoot extends SequentialCommandGroup {
  public AimAndShoot() {
    // aim = turret needs to aim to target before shooting
    // finished when turret aligned + flywheel up to speed
    var prepareFlywheel = Deadline.createDeadline(new PrepareFlywheel(), new SerializerStop(), new AimTurret()); 
    addCommands(prepareFlywheel);

    var prepareEjector = Deadline.createDeadline(new SetThroatSpeed(true), new PrepareFlywheel(), new SerializerStop(), new AimTurret());
    addCommands(prepareEjector);

    // aimTurret + flyWheel done (not finished) immediately, serializer never finishes (while held will interrupt)
    var shoot = Deadline.createDeadline(new SetThroatSpeed(true), new PrepareFlywheel(), new SerializerFeed(), new AimTurret()); // TODO: confim y/n for aim while shooting
    addCommands(shoot);
  }

}
