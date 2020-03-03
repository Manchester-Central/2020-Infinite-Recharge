/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util.dashboard;

import java.util.function.Consumer;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.LogUtils;

/**
 * An editable value that will automatically send/retrive from the Dashboard
 */
public abstract class BaseDashboardValue<T> {
    protected final String key;
    protected T value;
    private NetworkTableEntry dashboardEntry;

    /**
     * Creates a BaseDashboardValue that can be used to track values fom the dashboard
     * @param key - the name of the value to save to the dashboard
     * @param startingValue - the intitial value
     * @param onChangeFunction - a function that will be triggered whenever the value is changed on the dashboard
     */
    public BaseDashboardValue(String key, T startingValue, Consumer<T> onChangeFunction) {
        // Save the key and initial value
        this.key = key;
        set(startingValue);

        dashboardEntry = SmartDashboard.getEntry(key);

        // Set up an event listener for dashboard changes
        dashboardEntry.addListener(event -> {
            // For every new/updated event received, cache the converted value and call the onChangeFunction if supplied
            value = convertNetworkTableEntryToTargetType(event.value);
            LogUtils.log(String.format("BaseDashboardValue %s updated: %s", key, value.toString()));
            if(onChangeFunction != null) {
                onChangeFunction.accept(value);
            }
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
    }

    /**
     * Gets the current cached value
     */
    public T get(){
        return this.value;
    }

    /**
     * Sets the current value on the dashboard and caches it locally
     * @param value - the value to set
     */
    public void set(T value){
        this.value = value;
        putToDashboard(value);
    }

    /**
     * Converts the entry into the current target type
     * @param entry - the entry to convert
     */
    protected abstract T convertNetworkTableEntryToTargetType(NetworkTableValue entry);

    /**
     * Sets the current value on the dashboard (must be declared in subclasses)
     * @param value - the value to set
     */
    protected abstract void putToDashboard(T value);
}
