/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util.dashboard;

import java.util.function.Consumer;

import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.util.LogUtils;

/**
 * An updater class that will keep the P, I, and D of the dashboard synced down to the given PIDController
 */
public class PIDDashboardUpdater {
    private final NumberDashboardValue p, i, d;
    private final String entityKey;
    
    /**
     * Created a PIDDashboardUpdater with the specified arguments
     * @param entityKey - the name of the entity to create dashboard values for
     * @param startingP - the initial p
     * @param startingI - the initial i
     * @param startingD - the initial d
     * @param pChanged - the function to run when p is changed
     * @param iChanged - the function to run when i is changed
     * @param dChanged - the function to run when d is changed
     */
    private PIDDashboardUpdater(String entityKey, double startingP, double startingI, double startingD, Consumer<Double> pChanged, Consumer<Double> iChanged, Consumer<Double> dChanged) {
        this.entityKey = entityKey;
        p = new NumberDashboardValue(entityKey + "/pid/p", startingP, pChanged);
        i = new NumberDashboardValue(entityKey + "/pid/i", startingI, iChanged);
        d = new NumberDashboardValue(entityKey + "/pid/d", startingD, dChanged);
    }

    /**
     * Gets the current P from the dashboard
     */
    public double getP() {
        return p.get();
    }

    /**
     * Gets the current I from the dashboard
     */
    public double getI() {
        return i.get();
    }

    /**
     * Gets the current D from the dashboard
     */
    public double getD() {
        return d.get();
    }

    /**
     * Logs a message that displays the current P, I, and D values
     */
    public void logPID() {
        LogUtils.log(String.format("Default PID values for %s. p=%f, i=%f, d=%f", entityKey, getP(), getI(), getD()));
    }

    /**
     * Create a PIDDashboardUpdater for the PIDController from WPILib
     * @param pidControler - the pidController to update values for
     * @param entityKey - the name of the entity to create dashboard values for
     * @param startingP - the initial p
     * @param startingI - the initial i
     * @param startingD - the initial d
     */
    public static PIDDashboardUpdater createDashboardPID(PIDController pidControler, String entityKey, double startingP, double startingI, double startingD) {
        Consumer<Double> pChanged = (Double newValue) -> {
            pidControler.setP(newValue);
        };
        Consumer<Double> iChanged = (Double newValue) -> {
            pidControler.setI(newValue);
        };
        Consumer<Double> dChanged = (Double newValue) -> {
            pidControler.setD(newValue);
        };
        return new PIDDashboardUpdater(entityKey, startingP, startingI, startingD, pChanged, iChanged, dChanged);
    }

    /**
     * Create a PIDDashboardUpdater for the PIDController from a REV Robotics
     * @param pidControler - the pidController to update values for
     * @param entityKey - the name of the entity to create dashboard values for
     * @param startingP - the initial p
     * @param startingI - the initial i
     * @param startingD - the initial d
     */
    public static PIDDashboardUpdater createDashboardPID(CANPIDController pidControler, String entityKey, double startingP, double startingI, double startingD) {
        Consumer<Double> pChanged = (Double newValue) -> {
            pidControler.setP(newValue);
        };
        Consumer<Double> iChanged = (Double newValue) -> {
            pidControler.setI(newValue);
        };
        Consumer<Double> dChanged = (Double newValue) -> {
            pidControler.setD(newValue);
        };
        return new PIDDashboardUpdater(entityKey, startingP, startingI, startingD, pChanged, iChanged, dChanged);
    }
}
