/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Tells robot to do absolutely nothing. Immediately calls isFinished() Takes
 * zero parameters.
 */
public class NullCommand extends CommandBase {

    @Override
    public boolean isFinished() {
        return true;
    }

}
