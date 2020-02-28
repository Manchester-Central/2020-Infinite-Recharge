/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climbtake;

import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * Add your docs here.
 */
public interface IClimbTake2020 extends Subsystem {

  public void setPivotPosition(double target);

  public void setExtenderPosition(double target);

  public void setPivotPositionUNSAFE(double target);

  public void setExtensionPositionUNSAFE(double target);

  public double getPivotPosition();

  public double getExtensionPosition();

  public void goToLimit();

  public boolean getLimitSwitchState();

  public void setExtenderSpeed(double speed);

  public void setPivotSpeed(double speed);

  public void PIDDrivePivot();

  public void PIDDriveExtend();

  public void addToDashboard();

  public void smartdashboardConstants();
}
