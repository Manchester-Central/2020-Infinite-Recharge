/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.util.*;

public class Shoot extends CommandGroup {
  public Shoot(boolean aim) {
    // aim = turret needs to aim to target before shooting
   
    if (aim) {
      DoneCommand aimTurret = new AimTurret();
      DoneCommand flyWheel = new PrepareFlywheel();
      new Deadline(aimTurret, flyWheel).deadlineWith(aimTurret, flyWheel);

    } else {
      // addSequential(new PrepareFlywheel());
    }

    new RunCommand(new ShootOnce());

  }

}
