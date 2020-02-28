/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.unjammer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class Unjammer extends SubsystemBase implements IUnjammer {

  CANSparkMax unjammer;
  double unjammerSpeed;

  public Unjammer() {
    unjammer = new CANSparkMax(RobotConstants2020.UNJAMMER_SPARKMAX, MotorType.kBrushless);
    unjammerSpeed = 0.3;
  }

  @Override
  public void spin(boolean on) {
    if (on) {
      unjammer.set(unjammerSpeed);
    } else {
      unjammer.set(0);
    }
  }

  public void unJam() {
    unjammer.set(-1);
  }

}
