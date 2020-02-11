/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class Feeder extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Robot.RobotType type;
  
  public Feeder (Robot.RobotType robotType) {

    type = robotType;

    if (type == Robot.RobotType.raft) {
      return ;

    }
    if (type == Robot.RobotType.chaos2019) {
      return;

    }
    if (type == Robot.RobotType.chaos2020) {
      // feed = new CANSparkMax(RobotConstants2020.FEEDER_SPARKMAX, CANSparkMax.MotorType.kBrushless);
      // throat = new CANSparkMax(RobotConstants2020.THROAT_SPARKMAX, CANSparkMax.MotorType.kBrushless);
      // TODO: figure out port for Feeder and Throat and assign in RobotConstants2020, also verify motors are brushless
      return ;

    }

  }



  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
