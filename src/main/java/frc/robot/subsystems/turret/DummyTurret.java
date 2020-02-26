/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class DummyTurret extends SubsystemBase implements ITurret {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public double getPanAngle() {
    return 0;
  }

  public void setPanTarget(double target) {
    SmartDashboard.putNumber("Pan Target", target);
  }

  public void setPanSpeed(double speed) {
    SmartDashboard.putNumber("Pan Speed", speed);
  }

  public void setPanSpeedUnsafe(double speed) {
    SmartDashboard.putNumber("Pan Speed", speed);
  }
  
  public void setTiltSpeedUnsafe(double speed) {
    SmartDashboard.putNumber("Hood Speed", speed);
  }

  public void setTiltSpeed(double speed) {
    SmartDashboard.putNumber("Hood Speed", speed);
  }

  public double getTiltAngle() {
    return 0;
  }

  public void setTiltTargetAngle(double angle) {
    SmartDashboard.putNumber("HoodTarget", angle);
  }

  public void PIDDriveTilt() {
  }

  public void addTurretSmartDashboard() {
  }

  public void PIDDrive(boolean usingCamera) {
    PIDDriveTilt();
  }

  public void smartDashboardConstants() {
  }

  public void setTurretAngleDashboard() {
  }

  @Override
  public double getTiltTarget() {
    return 0;
  }

  @Override
  public double getPanTarget() {
    return 0;
  }

}
