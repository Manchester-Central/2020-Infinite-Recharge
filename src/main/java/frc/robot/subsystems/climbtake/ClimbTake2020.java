/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climbtake;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class ClimbTake2020 extends SubsystemBase implements IClimbTake2020 {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax pivot, extension;

  public ClimbTake2020(){
    pivot = new CANSparkMax(RobotConstants2020.ARM_SPARKMAX, CANSparkMax.MotorType.kBrushless);
    extension = new CANSparkMax(RobotConstants2020.CLIMB_SPARKMAX, CANSparkMax.MotorType.kBrushless);

  }

  public void setPivotPosition(double target) {
    
  } 

  public void setExtenderPosition(double target){
    
  }

  public void setPivotPositionUNSAFE(double target) {
    double speedScale = 0.25;
    pivot.set(target * speedScale);
  } 

  public void setExtensionPositionUNSAFE(double target) {
    double speedScale = 0.25;
    extension.set(target * speedScale);
  }

  public double getPivotPosition() {
    return 0;
  }

  public double getExtensionPosition() {
    return 0;
  }


}
