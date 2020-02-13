/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

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
      // flywheelLeft = new CANSparkMax(RobotConstants2020.FLYWHEEL1_SPARKMAX, CANSparkMax.MotorType.kBrushless);
      // flywheelRight = new CANSparkMax(RobotConstants2020.FLYWHEEL2_SPARKMAX, CANSparkMax.MotorType.kBrushless);
      // TODO: figure out port for FlywheelLeft and FlywheelRight, assign in RobotConstants2020, also verify motor is brushless
      return ;
      
    }

  }
}
