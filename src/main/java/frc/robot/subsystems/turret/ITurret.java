/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * Add your docs here.
 */
public interface ITurret extends Subsystem {
  public double getPanAngle();

  public void setPanTarget(double target);

  public void setPanSpeed(double speed);

  public void setPanSpeedUnsafe(double speed);

  public void setTiltSpeedUnsafe(double speed);

  public void setTiltSpeed(double speed);

  public double getTiltAngle();

  public void setTiltTargetAngle(double angle);

  public void addTurretSmartDashboard();

  public void PIDDrive(boolean usingCamera);

  public void smartDashboardConstants();

  public void setTurretAngleDashboard();

  public double getTiltTarget();

  public double getPanTarget();

  public void PIDDriveTilt();

}
