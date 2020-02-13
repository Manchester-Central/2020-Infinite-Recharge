/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


/**
 * Add your docs here.
 */
public class NavX extends SubsystemBase {

  AHRS sensor;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public NavX() {
    sensor = new AHRS(SPI.Port.kMXP);
  }

  public double getNavAngle() {
    return sensor.getAngle();
  }

  public double getNavPitch() {
    return sensor.getPitch();
  }

  public double getNavYaw() {
    return sensor.getYaw();
  }

  public void reset() {
    sensor.reset();
  }

  public void updateNavDashboard() {

    // post to smart dashboard periodically
    SmartDashboard.putNumber("NavX Angle", getNavAngle());
    SmartDashboard.putNumber("NavX Pitch", getNavPitch());
    SmartDashboard.putNumber("currentYaw", getNavYaw());
    SmartDashboard.putBoolean("Callibrating?", sensor.isCalibrating()); // not necessary long term
    if (sensor.isCalibrating()) {
      SmartDashboard.putBoolean("Callibrated?", sensor.isCalibrating());
    }
  }
}
