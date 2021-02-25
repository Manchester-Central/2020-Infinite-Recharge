/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.camera;

import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * Add your docs here.
 */
public interface ICamera extends Subsystem {
    public double getXAngle();

    public double getYAngle();

    public boolean hasTarget();

    public void setPipeline(double pipeline);

    public double getPipeline();

    // public void turnRobot(double angle) {

    // }

    public void updateDashboard();

    // Calculates angle
    // 2-degree offset hardcoded for raft testing
    public double getDistance();
}
