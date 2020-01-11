/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;



/**
 * Add your docs here.
 */

public class Camera {

    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    double robotHeight, totalHeight, offsetAngle;

    public Camera(double robotHeight, double totalHeight, double offsetAngle) {

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");

        this.robotHeight = robotHeight;
        this.totalHeight = totalHeight;
        this.offsetAngle = offsetAngle;



    }

    public double getYAngle(){
        return ty.getDouble(0.0);
    }

    public void updateDashboard() {

        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);

        SmartDashboard.putNumber("time", Math.random());
    }

    // Calculates angle
    // 2-degree offset hardcoded for raft testing
    public double getDistance() {
        double distance = (totalHeight - robotHeight) / Math.tan(Math.toRadians(getYAngle() + offsetAngle));
        SmartDashboard.putNumber("distance", distance);
        return distance;
    }

}
