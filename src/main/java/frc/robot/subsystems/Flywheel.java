/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class Flywheel extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Robot.RobotType type;

  public Flywheel (Robot.RobotType robotType) {

    type = robotType;

    if (type == Robot.RobotType.raft) {
      return ;

    }
    if (type == Robot.RobotType.chaos2019) {
      return;

    }
    if (type == Robot.RobotType.chaos2020) {
      flywheelA = new CANSparkMax(RobotConstants2020.FLYWHEELA_SPARKMAX, CANSparkMax.MotorType.kBrushless);
      flywheelB = new CANSparkMax(RobotConstants2020.FLYWHEELB_SPARKMAX, CANSparkMax.MotorType.kBrushless);
      // TODO: figure out port for FlywheelLeft and FlywheelRight, assign in RobotConstants2020, also verify motor is brushless
      return ;
      
    }

  }

  CANSparkMax flywheelA, flywheelB;

  public void setTargetSpeed(double speed) {

  }

  public void setFlywheelTargetDirect(double speed) {

  }

}
