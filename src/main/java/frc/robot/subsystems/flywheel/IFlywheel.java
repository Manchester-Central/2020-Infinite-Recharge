/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.flywheel;

import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * Add your docs here.
 */
public interface IFlywheel extends Subsystem {

    public String FLYWHEEL_TARGET = "Flywheel target speed RPM";

    public void accelerateToSetPoint();

    public void setTarget(double target);
    
    public double getFlywheelSpeed();
  
    public void setFlywheelTargetDirect(double speed);
  
    public void addFlywheelSmartDashboard();

    public void coastFlywheel();

    public double getCurrentFlywheelRPM();
}
