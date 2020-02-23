/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.navx;

import edu.wpi.first.wpilibj2.command.Subsystem;


/**
 * Add your docs here.
 */
public interface INavX extends Subsystem {


  public double getNavAngle();

  public double getNavPitch();

  public double getNavYaw();

  public void reset();

  public void updateNavDashboard();
}
