/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.climbtake;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
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
  DigitalInput limitSwitch;
  double pivotBottomPosition, extensionBottomPosition;

  public ClimbTake2020() {
    pivot = new CANSparkMax(RobotConstants2020.ARM_SPARKMAX, CANSparkMax.MotorType.kBrushless);
    extension = new CANSparkMax(RobotConstants2020.CLIMB_SPARKMAX, CANSparkMax.MotorType.kBrushless);
    limitSwitch = new DigitalInput(RobotConstants2020.LIMIT_SWITCH);
    pivotPot = new AnalogInput(RobotConstants2020.CLIMB_POT);
    pivot.setInverted(true);
    resetExtendEncoder();

    pivotP = 0.75;
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
    
    SmartDashboard.putNumber("Pivot Setpoint", pidPivot.getSetpoint());


    extensionBottomPosition = getExtensionPosition();

    slowSpeed = RobotConstants2020.PIVOT_SLOW_SPEED; // TODO: check values with Dan
    pivotThreshold = RobotConstants2020.PIVOT_THRESHOLD; // TODO: check values with Dan
  }

  double pivotP, pivotI, pivotD, extendP, extendI, extendD;
  PIDController pidPivot, pidExtend;
  double maxExtendSpeed = 0.4;
  double maxPivotSpeed = 0.2;
  AnalogInput pivotPot;
  double slowSpeed, pivotThreshold;

  private void resetExtendEncoder() {
    extension.getEncoder().setPosition(0);
  }

  public void setPivotPosition(double target) {
    pidPivot.setSetpoint(target);
    SmartDashboard.putNumber("Pivot Setpoint", pidPivot.getSetpoint());
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

    double kSet = SmartDashboard.getNumber("Pivot Setpoint", pidPivot.getSetpoint());

    if (kSet != pidPivot.getSetpoint()) {
      pidPivot.setSetpoint(kSet);
    }
    
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
    return pivotPot.getVoltage();
  }

  public double getExtensionPosition() {
    return extension.getEncoder().getPosition();
  }

  public void setPivotSpeed(double speed) {
    if (getLimitSwitchState() && speed < 0) { // check direction of speed
      pivot.set(0);
    } else if ((getPivotPosition() < pivotThreshold) && (speed < 0)){
      pivot.set(Math.min(speed, slowSpeed));
    } else {
      pivot.set(speed);
    }
  }

  public void setExtenderSpeed(double speed) {
    extension.set(speed);
  }

  public boolean getLimitSwitchState() {
    return limitSwitch.get();
  }

  public void goToLimit() {
    if (!getLimitSwitchState()) {
      double speed = -0.25;
      setPivotSpeed(speed * maxPivotSpeed);
    } else {
      pivotBottomPosition = getPivotPosition();
    }
  }
  
  public void PIDDriveExtend() {
    double speed = pidExtend.calculate(getExtensionPosition());
    setExtenderSpeed(speed * maxExtendSpeed);
  }
  
  public void PIDDrivePivot() {
    double speed = pidPivot.calculate(getPivotPosition());
    SmartDashboard.putNumber("Pivot Output", speed);
    setPivotSpeed(speed);
  }

  public void addToDashboard() {
    SmartDashboard.putNumber("Pivot position", getPivotPosition());
    SmartDashboard.putNumber("Extension position", getExtensionPosition());
    
  }


}
