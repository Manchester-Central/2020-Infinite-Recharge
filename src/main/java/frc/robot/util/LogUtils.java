/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A class to to  log  things based on a static flag 'loggingEnabled'
 */
public class LogUtils {
    private LogUtils() {}
    public static boolean loggingEnabled = false;

    public static void log(String message) {
        if(loggingEnabled){
            System.out.println(message);
        }
    } 

    public static void log(String key, String value) {
        if(loggingEnabled){
            System.out.println(key + "\n\t" +  value);
            SmartDashboard.putString(key, value);
        }
    } 

    public static void log(String key, double value) {
        if(loggingEnabled){
            System.out.println(key + "\n\t" +  value);
            SmartDashboard.putNumber(key, value);
        }
    }

}
