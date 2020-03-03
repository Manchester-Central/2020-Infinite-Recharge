/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util.dashboard;

import java.util.function.Consumer;

import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An editable number that will automatically send/retrive from the Dashboard
 */
public class NumberDashboardValue extends BaseDashboardValue<Double> {

    /**
     * Creates a NumberDashboardValue that can be used to track values fom the dashboard
     * @param key - the name of the value to save to the dashboard
     * @param startingValue - the intitial value
     */
    public NumberDashboardValue(String key, Double startingValue) {
        super(key, startingValue, null);
    }

    /**
     * Creates a NumberDashboardValue that can be used to track values fom the dashboard
     * @param key - the name of the value to save to the dashboard
     * @param startingValue - the intitial value
     * @param onChangeFunction - a function that will be triggered whenever the value is changed on the dashboard
     */
    public NumberDashboardValue(String key, Double startingValue, Consumer<Double> onChangeFunction) {
        super(key, startingValue, onChangeFunction);
    }

    @Override
    protected void putToDashboard(Double value) {
        SmartDashboard.putNumber(key, value);
    }

    @Override
    protected Double convertNetworkTableEntryToTargetType(NetworkTableValue entry) {
        return entry.getDouble();
    }
}
