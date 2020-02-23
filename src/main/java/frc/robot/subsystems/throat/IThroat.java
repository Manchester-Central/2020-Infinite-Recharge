/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.throat;

import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * Add your docs here.
 */
public interface IThroat extends Subsystem{
    
    public void ejectorSpeed(boolean on);
}
