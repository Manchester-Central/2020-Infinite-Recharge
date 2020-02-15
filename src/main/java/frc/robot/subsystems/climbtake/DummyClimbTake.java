/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climbtake;

/**
 * Add your docs here.
 */
public class DummyClimbTake implements IClimbTake2019, IClimbTake2020 {

  public void setPivotPosition(double target) {
  }

  public void setExtensionPosition(double target) {
  }

  public void setPivotPositionUNSAFE(double target) {
  }

  public void setExtensionPositionUNSAFE(double target) {
  }

  public double getPivotPosition() {
    return 0;
  }

  public double getExtensionPosition() {
    return 0;
  }

  @Override
  public void periodic() {

  }

  @Override
  public void setArmSpeed(double speed) {

  }

  @Override
  public double getAngle() {
    return 0;
  }

  @Override
  public void setTargetAngle(double angle) {

  }

  @Override
  public void PIDDrive() {

  }

}
