/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climbtake;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants2020;

/**
 * Add your docs here.
 */
public class ClimbTake2020 extends SubsystemBase implements IClimbTake2020 {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax pivot, extension;

  public ClimbTake2020() {
    pivot = new CANSparkMax(RobotConstants2020.ARM_SPARKMAX, CANSparkMax.MotorType.kBrushless);
    extension = new CANSparkMax(RobotConstants2020.CLIMB_SPARKMAX, CANSparkMax.MotorType.kBrushless);
    
    pivotP = 0;
    pivotI = 0;
    pivotD = 0;
    extendP = 0;
    extendI = 0;
    extendD = 0;
    pidPivot = new PIDController(pivotP, pivotI, pivotD);
    pidExtend = new PIDController(extendP, extendI, extendD);

    SmartDashboard.putNumber("Pivot P", pivotP);
    SmartDashboard.putNumber("Pivot I", pivotI);
    SmartDashboard.putNumber("Pivot D", pivotD);
    SmartDashboard.putNumber("Extension P", extendP);
    SmartDashboard.putNumber("Extension I", extendI);
    SmartDashboard.putNumber("Extension D", extendD);

  }

  double pivotP, pivotI, pivotD, extendP, extendI, extendD;
  PIDController pidPivot, pidExtend;
  double maxExtendSpeed = 0.4;
  double maxPivotSpeed = 0.4;

  public void setPivotPosition(double target) {
    pidPivot.setSetpoint(target);
  }

  public void setExtenderPosition(double target) {
    pidExtend.setSetpoint(target);
  }

  public void setPivotPositionUNSAFE(double target) {
    double speedScale = 0.25;
    pivot.set(target * speedScale);
  }

  public void setExtensionPositionUNSAFE(double target) {
    double speedScale = 0.25;
    extension.set(target * speedScale);
  }

  public void smartdashboardConstants() {
    double kPP = SmartDashboard.getNumber("Pivot P", 0);
    double kPI = SmartDashboard.getNumber("Pivot I", 0);
    double kPD = SmartDashboard.getNumber("Pivot D", 0);

    double kEP = SmartDashboard.getNumber("Extension P", 0);
    double kEI = SmartDashboard.getNumber("Extension I", 0);
    double kED = SmartDashboard.getNumber("Extension D", 0);

    
    if ((kPP != pivotP)) {
      pidPivot.setP(kPP);
      pivotP = kEP;
    }
    if ((kPI != pivotI)) {
      pidPivot.setI(kPI);
      pivotI = kEI;
    }
    if ((kPD != pivotD)) {
      pidPivot.setD(kPD);
      pivotD = kPD;
    }

    if ((kEP != extendP)) {
      pidExtend.setP(kEP);
      extendP = kEP;
    }
    if ((kEI != extendI)) {
      pidExtend.setI(kEI);
      extendI = kEI;
    }
    if ((kED != extendD)) {
      pidExtend.setD(kED);
      extendD = kED;
    }
  }

  public double getPivotPosition() {
    return 0;
  }

  public double getExtensionPosition() {
    return 0;
  }
  
  public void PIDDriveExtend() {
    double speed = pidExtend.calculate(getExtensionPosition());
    setExtenderPosition(speed * maxExtendSpeed);
    System.out.println("Hood angle: " + getExtensionPosition() + " hood speed: " + speed);
  }
  
  public void PIDDrivePivot() {
    double speed = pidExtend.calculate(getPivotPosition());
    setPivotPosition(speed * maxPivotSpeed);
    System.out.println("Hood angle: " + getPivotPosition() + " hood speed: " + speed);
  }

}
