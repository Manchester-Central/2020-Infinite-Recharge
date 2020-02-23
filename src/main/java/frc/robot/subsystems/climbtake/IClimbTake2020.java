/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climbtake;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public interface IClimbTake2020 extends Subsystem {

  public void setPivotPosition(double target);

  public void setExtensionPosition(double target);

  public void setPivotPositionUNSAFE(double target);

  public void setExtensionPositionUNSAFE(double target);

  public double getPivotPosition();

  public double getExtensionPosition();

}
