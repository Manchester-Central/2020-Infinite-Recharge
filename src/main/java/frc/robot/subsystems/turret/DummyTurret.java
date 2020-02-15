/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;

import javax.swing.text.StyleContext.SmallAttributeSet;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

/**
 * Add your docs here.
 */
public class DummyTurret extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public DummyTurret(Robot.RobotType robotType) {
  }

  public double getXPosition() {
    return 0;
  }

  public void setXTarget(double target) {
  }

  public void setXSpeedUnsafe(double speed) {
  }

  public void setYSpeedUnsafe(double speed) {
  }

  public void setHoodSpeed(double speed) {
  }

  public double getHoodAngle() {
    return 0;
  }

  public void setHoodTargetAngle(double angle) {
  }

  public void PIDDriveY() {
  }

  public void addTurretSmartDashboard(){
  }

  public void PIDDrive() {
  }
}
