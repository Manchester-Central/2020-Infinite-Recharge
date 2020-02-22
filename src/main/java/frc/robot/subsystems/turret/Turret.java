/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;

import javax.swing.text.StyleContext.SmallAttributeSet;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

/**
 * Add your docs here.
 */
public class Turret extends SubsystemBase implements ITurret {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Turret() {
    xP = 0;
    xI = 0;
    xD = 0;
    yP = 0;
    yI = 0;
    yD = 0;
    pidX = new PIDController(xP, xI, xD);
    pidY = new PIDController(yP, yI, yD);

    slopeX = RobotConstants2020.ANGLE_POT_SLOPE_X; // TODO: CHANGE!!!
    interceptX = RobotConstants2020.ANGLE_POT_INTERCEPT_X; // TODO: CHANGE!!!
    slopeY = RobotConstants2020.ANGLE_POT_SLOPE_Y; // TODO: CHANGE!!!
    interceptY = RobotConstants2020.ANGLE_POT_INTERCEPT_Y; // TODO: CHANGE!!!

    int channelX = RobotConstants2020.ANGLE_POT_X; // TODO: CHANGE!!!!!!!!!!!!!!!!!!!!
    int channelY = RobotConstants2020.ANGLE_POT_Y; // TODO: CHANGE!!!!!!!!!!!!!!!!!!!!

    speedControllerX = new WPI_TalonSRX(RobotConstants2020.TURRET_PAN);
    speedControllerY = new WPI_TalonSRX(RobotConstants2020.TURRET_HOOD);

    minAngleX = RobotConstants2020.MIN_ANGLE_TURRET_PAN;
    maxAngleX = RobotConstants2020.MAX_ANGLE_TURRET_PAN;
    minAngleY = RobotConstants2020.MIN_ANGLE_TURRET_HOOD;
    maxAngleY = RobotConstants2020.MAX_ANGLE_TURRET_HOOD;

    // set PID coefficients
    pidX.setP(xP);
    pidX.setI(xI);
    pidX.setD(xD);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain PAN", xP);
    SmartDashboard.putNumber("I Gain PAN", xI);
    SmartDashboard.putNumber("D Gain PAN", xD);

    // set PID coefficients
    pidY.setP(yP);
    pidY.setI(yI);
    pidY.setD(yD);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain TILT", yP);
    SmartDashboard.putNumber("I Gain TILT", yI);
    SmartDashboard.putNumber("D Gain TILT", yD);
  }

  private double xP, xI, xD, yP, yI, yD;
  private double minAngleX, minAngleY, maxAngleX, maxAngleY;
  private double slopeX, slopeY, interceptX, interceptY;
  PIDController pidX, pidY;
  WPI_TalonSRX speedControllerX, speedControllerY;

  private double turretXDegrees(CANSparkMax input) {
    double gearRatio = (double) 10 / 1; // ratio of the axel the turntable lies on to the axel the encoder reads
    int ticksPerRev = 42; // amount of ticks in one revolution of the encoder axel
    double counts = input.getEncoder().getPosition();
    return (counts * gearRatio) / ticksPerRev;
  }

  public double getXPosition() {
    // make sure the slope and intercept are accounted for
    return speedControllerX.getSensorCollection().getAnalogIn();
  }

  public void setXTarget(double target) {
    pidX.setSetpoint(target);
  }

  public void setXSpeed(double speed) {
    if (speedControllerX.getSensorCollection().getAnalogInRaw() <= RobotConstants2020.MIN_PAN_RAW && speed < 0) {
      speed = 0;
    }
    if (speedControllerX.getSensorCollection().getAnalogInRaw() >= RobotConstants2020.MAX_PAN_RAW && speed > 0) {
      speed = 0;
    }
    speedControllerX.set(speed);
  }

  public void setXSpeedUnsafe(double speed) { // pan
    double multiplier = 0.05;

    speedControllerX.set(speed * multiplier); // DANGER!!
    SmartDashboard.putNumber("Pan (X) speed joystick", speed);
  }

  public void setYSpeedUnsafe(double speed) { // tilt
    double multiplier = 0.5;
    speedControllerY.set(speed * multiplier); // DANGER!!
    SmartDashboard.putNumber("Tilt (Y) speed joystick", speed);
  }

  private void PIDDriveX() { // pan
    double maxSpeed = 0.4;
    double speed = pidX.calculate(getXPosition());
    setXSpeed(speed * maxSpeed);
    System.out.println("Turret pan angle: " + getXPosition() + " pan speed: " + speed);
  }

  public void setHoodSpeed(double speed) {
    if (speedControllerY.getSensorCollection().getAnalogInRaw() <= RobotConstants2020.MIN_HOOD_RAW && speed < 0) {
      speed = 0;
    }
    if (speedControllerY.getSensorCollection().getAnalogInRaw() >= RobotConstants2020.MAX_HOOD_RAW && speed > 0) {
      speed = 0;
    }
    SmartDashboard.putNumber("Turret Y Speed", speed);
    speedControllerY.set(speed);
  }

  public double getHoodAngle() {
    return speedControllerY.getSensorCollection().getAnalogIn();
  }

  public void setHoodTargetAngle(double angle) {
    double mathAngle = angle;
    pidY.setSetpoint(mathAngle);
  }

  public void PIDDriveY() { // tilt
    double maxSpeed = 0.4;
    double speed = pidY.calculate(getHoodAngle());
    setHoodSpeed(speed * maxSpeed);
    System.out.println("Hood angle: " + getHoodAngle() + " hood speed: " + speed);
  }

  public void addTurretSmartDashboard(){
    SmartDashboard.putNumber("Y Potentiometer, tilt", getHoodAngle());
    SmartDashboard.putNumber("X Potentiometer, pan", getXPosition());
    SmartDashboard.putNumber("Pan Raw", speedControllerX.getSensorCollection().getAnalogInRaw());
    SmartDashboard.putNumber("Tilt Raw", speedControllerY.getSensorCollection().getAnalogInRaw());
  }

  public void smartDashboardConstants() {
    double kXP = SmartDashboard.getNumber("P Gain PAN", 0);
    double kXI = SmartDashboard.getNumber("I Gain PAN", 0);
    double kXD = SmartDashboard.getNumber("D Gain PAN", 0);

    double kYP = SmartDashboard.getNumber("P Gain TILT", 0);
    double kYI = SmartDashboard.getNumber("I Gain TILT", 0);
    double kYD = SmartDashboard.getNumber("D Gain TILT", 0);

    
    if ((kXP != xP)) {
      pidX.setP(kXP);
      xP = kXP;
    }
    if ((kXI != xI)) {
      pidX.setI(kXI);
      xI = kXI;
    }
    if ((kXD != xD)) {
      pidX.setD(kXD);
      xD = kXD;
    }

    if ((kYP != yP)) {
      pidY.setP(kYP);
      yP = kYP;
    }
    if ((kYI != yI)) {
      pidY.setI(kYI);
      yI = kYI;
    }
    if ((kYD != yD)) {
      pidY.setD(kYD);
      yD = kYD;
    }

  }

  public void PIDDrive() {
    PIDDriveX();
    PIDDriveY();
  }
}
