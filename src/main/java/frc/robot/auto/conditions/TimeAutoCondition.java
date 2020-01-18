/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.conditions;

/**
 * Add your docs here.
 */
public class TimeAutoCondition implements IAutoCondition {

    long startTime;
    int timeMs;

    public TimeAutoCondition(int timeMs) {
        this.timeMs = timeMs;
    }

    @Override
    public void init() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isDone() {
        return (System.currentTimeMillis() - startTime) > timeMs;
    }
}
