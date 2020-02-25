/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.serializer.SerializerFeed;
import frc.robot.commands.util.*;

public class Shoot extends SequentialCommandGroup {
  public Shoot() {
    // aim = turret needs to aim to target before shooting

    DoneCommand aimTurret = new AimTurret(); // done when turret aligned
    DoneCommand flyWheel = new PrepareFlywheel(); // done when flywheel up to speed
    DoneCommand serializer = new SerializerFeed(); // never done!!
    
    var prepare = Deadline.createDeadline(aimTurret, flyWheel); // finished when turret aligned + flywheel up to speed
    addCommands(prepare);

    // aimTurret + flyWheel done (not finished) immediately, serializer never finishes (while held will interrupt)
    var shoot = Deadline.createDeadline(aimTurret, flyWheel, serializer); 
    addCommands(shoot);


  }

}
