/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.serializer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class DummySerializer extends SubsystemBase implements ISerializer {

  public void driveTurnTable(SerializerSpeed speed, boolean ejectorOn) {
  }

  public void ejectorSpeed(double speed){
  }
  
  public void manualSpeed(double speed){
  }
}