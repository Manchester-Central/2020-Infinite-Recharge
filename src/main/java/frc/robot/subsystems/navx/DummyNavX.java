/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.navx;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class DummyNavX extends SubsystemBase implements INavX {

  AHRS sensor;


  public DummyNavX() {
  }

  public double getNavAngle() {
    return 0;
  }

  public double getNavPitch() {
    return 0;
  }

  public double getNavYaw() {
    return 0;
  }

  public void reset() {
  }

  public void updateNavDashboard() {
  }
}
