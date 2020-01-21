/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.conditions;

/**
 * Stops auto command if based on time.
 * Requires int "timeMs" in parameter (in milliseconds)
 */
public class TimeAutoCondition implements IAutoCondition {

    long startTime; // stores current time at init
    int timeMs; // stores target time

    public TimeAutoCondition(int timeMs) {
        this.timeMs = timeMs;
    }

    @Override
    public void init() {
        startTime = System.currentTimeMillis();
    }

    // when the total time is greater than 5, stop time auto command
    @Override
    public boolean isDone() {
        return (System.currentTimeMillis() - startTime) > timeMs;
    }
}
