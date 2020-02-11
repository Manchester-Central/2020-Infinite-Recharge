/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto.conditions;

import frc.robot.Robot;
import frc.robot.Robot.*;

/**
 * Add your docs here.
 */
public class InDrivePositionCondition implements IAutoCondition {

    @Override
    public void init() {

    }

    @Override
    public boolean isDone() {
        return Robot.driveBase.isAtTarget();
    }


}
