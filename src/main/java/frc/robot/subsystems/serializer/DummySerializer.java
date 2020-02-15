/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.serializer;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.serializer.*;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class DummySerializer extends SubsystemBase implements ISerializer {

  public DummySerializer(Robot.RobotType robotType) {

  }
  
  public void driveTurnTable(SerializerSpeed speed, boolean ejectorOn) {
  }
}