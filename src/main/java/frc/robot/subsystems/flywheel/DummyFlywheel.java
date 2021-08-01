/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.flywheel;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class DummyFlywheel extends SubsystemBase implements IFlywheel {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  double setPoint;
  public final String FLYWHEEL_TARGET = "Flywheel target speed RPM";
  public final String DUMMY_FLYWHEEL_CURRENT_SPEED = "Dummy flywheel current speed";

  public DummyFlywheel() {

    SmartDashboard.putNumber(DUMMY_FLYWHEEL_CURRENT_SPEED, 0);
  }

  public void accelerateToSetPoint() {
    // SmartDashboard.putNumber("Flywheel setPoint", setPoint);
    // SmartDashboard.putNumber("Dummy flywheel time", System.currentTimeMillis());
    SmartDashboard.putBoolean("Is Flywheel Accelerating", true);

  }

  public double getFlywheelSpeed() {
    return SmartDashboard.getNumber(DUMMY_FLYWHEEL_CURRENT_SPEED, 0);
  }

  public void setFlywheelTargetDirect(double power) {
    SmartDashboard.putNumber("Flywheel target power", power);
    SmartDashboard.putBoolean("Is Flywheel Accelerating", false);

  }

  public void addFlywheelSmartDashboard() {
  }

  @Override
  public void setTarget(double target) {
    setPoint = target;
    SmartDashboard.putNumber(FLYWHEEL_TARGET, target);
  }

  public double getFlywheelSetPoint() {
    return setPoint;
  }

  @Override
  public void coastFlywheel() {
    // SmartDashboard.putNumber("Current Flywheel RPM", setPoint);
    SmartDashboard.putBoolean("Is Flywheel Accelerating", false);
  }

  @Override
  public double getCurrentFlywheelRPM() {
    return getFlywheelSpeed();
  }
}