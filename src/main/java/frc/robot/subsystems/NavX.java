/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class NavX extends Subsystem {

  AHRS ahrs;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public NavX(){
    ahrs = new AHRS(SPI.Port.kMXP); 
  }

  public double getNavAngle() {
    return ahrs.getAngle();
  }

  public double getNavPitch() {
    return ahrs.getPitch();
  }

  public void updateNavDashboard() {

    // post to smart dashboard periodically
    SmartDashboard.putNumber("NavX Angle", getNavAngle());
    SmartDashboard.putNumber("NavX Pitch", getNavPitch());

}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
