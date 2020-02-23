/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climbtake;

import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * 2 speed controllers + potentiometer Make PID that takes arm angle from
 * potentiometer + angle from limelight to adjust
 */
public interface IClimbTake2019 extends Subsystem {

  public void periodic();

  public void setArmSpeed(double speed);

  public double getAngle();

  public void setTargetAngle(double angle);

  public void PIDDrive();
}
