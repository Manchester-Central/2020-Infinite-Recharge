/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.throat;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class Throat extends SubsystemBase implements IThroat{
  
  private CANSparkMax ejector;

  public Throat(){
    ejector = new CANSparkMax(RobotConstants2020.EJECTER_SPARKMAX, MotorType.kBrushless);
    ejector.setInverted(true);
  
  }

  /*

  if (ejectorOn){
    ejector.set(1);
  }else {
    ejector.set(0);
  }

  */

  public void ejectorSpeed(boolean on) {
    if (!on) {
      ejector.set(0);
      return;
    }
    ejector.set(1);
  }


  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  
}