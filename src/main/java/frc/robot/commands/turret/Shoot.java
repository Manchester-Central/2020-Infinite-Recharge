/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.util.*;

public class Shoot extends SequentialCommandGroup {
  public Shoot(boolean aim) {
    // aim = turret needs to aim to target before shooting
   
    if (aim) {
      DoneCommand aimTurret = new AimTurret();
      DoneCommand flyWheel = new PrepareFlywheel();
      var deadline = Deadline.createDeadline(aimTurret, flyWheel);
      addCommands(deadline);

    } else {
      // TODO implement non-aiming mode
      // addSequential(new PrepareFlywheel());
    }

    addCommands(new ShootOnce());

  }

}
