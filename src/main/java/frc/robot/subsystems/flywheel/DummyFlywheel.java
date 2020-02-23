/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.flywheel;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class DummyFlywheel extends SubsystemBase implements IFlywheel {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public DummyFlywheel() {
  }

  public void setTargetSetpoint(double speed) {
  }

  

  public double getFlywheelSpeed() {
    return 0;
  }

  public void setFlywheelTargetDirect(double speed) {
  }

  public void addFlywheelSmartDashboard() {
  }

  @Override
  public void setFlywheelTargetDashboard() {

  }

}