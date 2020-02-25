/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class Intake extends SubsystemBase implements IIntake{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonFX intake;

  public Intake () {
    // intake = new TalonFX(RobotConstants2020.INTAKE_FALCON);
  }
  
  public void setSpeedIntake(double speed) {

  }
}
