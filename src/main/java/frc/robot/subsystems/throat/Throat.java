/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.throat;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.RobotController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class Throat extends SubsystemBase implements IThroat {

  private CANSparkMax ejector;

  public Throat() {
    setUpEjector();
  }

  public void setUpEjector() {
    if(ejector != null) {
      ejector.disable();
    }
    ejector = new CANSparkMax(RobotConstants2020.EJECTER_SPARKMAX, MotorType.kBrushless);
    ejector.setInverted(true);
    ejector.setIdleMode(CANSparkMax.IdleMode.kCoast);
  }

  private void set(double value)
  {
    long start = RobotController.getFPGATime();
    ejector.set(value);
    SmartDashboard.putNumber("ThroatSetTime_ms", (RobotController.getFPGATime() - start) / 1000);
  }

  public void ejectorSpeed(boolean on) {
    if (!on) {
      set(0);
      return;
    }
    set(1);

  }

  public double getThroatSpeed() {
    return ejector.getEncoder().getVelocity();
  }

  public void unJam() {
    set(-1); // TODO: check with Dan for magnitude
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void periodic()
  {
    SmartDashboard.putNumber("ThroatFaults", ejector.getFaults());
    SmartDashboard.putNumber("ThroatSpeed", getThroatSpeed());

  }
}
