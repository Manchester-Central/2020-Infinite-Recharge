/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class Intake extends SubsystemBase implements IIntake{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax intake;

  public Intake () {
    intake = new CANSparkMax(RobotConstants2020.ARM_SPARKMAX, CANSparkMax.MotorType.kBrushless);
  }
  
  public void setSpeedIntake(double speed) {

  }
}
