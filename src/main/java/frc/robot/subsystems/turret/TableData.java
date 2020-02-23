/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

/**
 * Add your docs here.
 */
public class TableData {

    private double distance;
    private double speed;
    private double angle;

    public TableData(double dist, double spd, double ang) {

        distance = dist;
        speed = spd;
        angle = ang;

    }

    public double getDistance() {
        return distance;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAngle() {
        return angle;
    }

}
