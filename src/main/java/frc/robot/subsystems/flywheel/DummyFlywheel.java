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

  public DummyFlywheel() {
  }

  public void setTargetSetpoint(double setPoint) {

    SmartDashboard.putNumber("Flywheel setPoint", setPoint);

  }

  public double getFlywheelSpeed() {
    return 0;
  }

  public void setFlywheelTargetDirect(double speed) {
    SmartDashboard.putNumber("Flywheel target speed", speed);

  }

  public void addFlywheelSmartDashboard() {
  }

  @Override
  public void setFlywheelTargetDashboard() {
    setTargetSetpoint(SmartDashboard.getNumber("Flywheel target speed RPM", 0));
  }

}