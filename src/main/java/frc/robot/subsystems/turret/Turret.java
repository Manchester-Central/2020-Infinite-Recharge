/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants2020;
import frc.robot.util.LogUtils;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class Turret extends SubsystemBase implements ITurret {
  final boolean tuning = false;

  private double panP, panI, panD, tiltP, tiltI, tiltD, camPanP, camPanI, camPanD;

  private int minRawTilt, maxRawTilt, minRawPan, maxRawPan;


  // private double minAnglePan, maxAnglePan;
  // private double slopePan, interceptPan;
  PIDController pidPan, pidTilt, camPanPID;
  WPI_TalonSRX speedControllerPan, speedControllerTilt;
  private double panError, tiltError;

  double panMultiplier = 0.05;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Turret() {
    panP = 0.055;
    panI = 0.02;
    panD = 0.015;
    camPanP = 0.07;
    camPanI = 0.02;
    camPanD = 0;
    tiltP = 0.4;
    tiltI = 0;
    tiltD = 0;

    camPanPID = new PIDController(camPanP, camPanI, camPanD);
    pidPan = new PIDController(panP, panI, panD);
    pidTilt = new PIDController(tiltP, tiltI, tiltD);

    speedControllerPan = new WPI_TalonSRX(RobotConstants2020.TURRET_PAN);
    speedControllerTilt = new WPI_TalonSRX(RobotConstants2020.TURRET_HOOD);
    speedControllerTilt.setInverted(true);

    speedControllerPan.configOpenloopRamp(0.075);

    minRawPan = RobotConstants2020.MIN_PAN_RAW;
    maxRawPan = RobotConstants2020.MAX_PAN_RAW;
    minRawTilt = RobotConstants2020.MIN_HOOD_RAW;
    maxRawTilt = RobotConstants2020.MAX_HOOD_RAW;

    // set PID coefficients
    pidPan.setP(panP);
    pidPan.setI(panI);
    pidPan.setD(panD);

    if (tuning) {
      // display PID coefficients on SmartDashboard
      SmartDashboard.putNumber("P Gain PAN", panP);
      SmartDashboard.putNumber("I Gain PAN", panI);
      SmartDashboard.putNumber("D Gain PAN", panD);
    }
    // set camera PID coefficients
    pidPan.setP(camPanP);
    pidPan.setI(camPanI);
    pidPan.setD(camPanD);

    if (tuning) {
      // display camera PID coefficients on SmartDashboard
      SmartDashboard.putNumber("Camera P Gain PAN", camPanP);
      SmartDashboard.putNumber("Camera I Gain PAN", camPanI);
      SmartDashboard.putNumber("Camera D Gain PAN", camPanD);
    }

    // set PID coefficients
    pidTilt.setP(tiltP);
    pidTilt.setI(tiltI);
    pidTilt.setD(tiltD);

    if (tuning) {
      // display PID coefficients on SmartDashboard
      SmartDashboard.putNumber("P Gain TILT", tiltP);
      SmartDashboard.putNumber("I Gain TILT", tiltI);
      SmartDashboard.putNumber("D Gain TILT", tiltD);
    }

    // int rawRange = maxRawPan - minRawPan;
    // double angleRange = maxAnglePan - minAnglePan;
    // slopePan = angleRange/rawRange;
    // interceptPan = maxAnglePan - (slopePan * maxRawPan);

    panError = 0.1;
    tiltError = 0.1;

  }

  private double turretPanRawToDegrees(double raw) {
    // return ((-0.0062 * raw * raw) + (4.418 * raw) - 684.81);
    // return ((-0.0079 * raw * raw) + (5.36 * raw) - 795);
    return ((-0.0025 * raw * raw) + (2.6286 * raw) - 599.72);
  }

  public double getPanAngle() {
    double raw = speedControllerPan.getSensorCollection().getAnalogInRaw();
    // TODO: make sure the slope and intercept are accounted for
    return turretPanRawToDegrees(raw);
  }

  public void setPanTarget(double target) {
    pidPan.setSetpoint(target);
    camPanPID.setSetpoint(target);
    SmartDashboard.putNumber("Actual Pan Target", target);
  }

  public void setPanSpeed(double speed) {
    double currentPanPosition = speedControllerPan.getSensorCollection().getAnalogInRaw();
    double slowdownThreshold = RobotConstants2020.PAN_SLOWDOWN_THRESHOLD;
    double currentAngle = getPanAngle(); 
    double minAngle = turretPanRawToDegrees(minRawPan);
    double maxAngle = turretPanRawToDegrees(maxRawPan);
    double distanceToMin = Math.abs(currentAngle - minAngle);
    double distanceToMax = Math.abs(currentAngle - maxAngle);

    if (currentPanPosition <= minRawPan && speed < 0) {
      speed = 0;
    }
    else if (distanceToMin <= slowdownThreshold && speed < 0) {
      var percentToLimit = distanceToMin / slowdownThreshold;
      speed = Math.min(RobotConstants2020.PAN_MIN_POWER * -1.0, speed * percentToLimit);
    }
    else if (currentPanPosition >= maxRawPan && speed > 0) {
      speed = 0;
    }
    else if (distanceToMax <= slowdownThreshold && speed > 0) {
      var percentToLimit = distanceToMax / slowdownThreshold;
      speed = Math.max(RobotConstants2020.PAN_MIN_POWER, speed * percentToLimit);
    }

    speedControllerPan.set(speed);
  }

  public void setPanSpeedUnsafe(double speed) { // pan
    speedControllerPan.set(speed * panMultiplier); // DANGER!!
    SmartDashboard.putNumber("Pan (X) speed joystick", speed);
  }

  public void setTiltSpeedUnsafe(double speed) { // tilt
    double multiplier = 0.5;
    speedControllerTilt.set(speed * multiplier); // DANGER!!
    SmartDashboard.putNumber("Tilt (Y) speed joystick", speed);
  }

  private void PIDDrivePan(boolean usingCamera) { // pan
    double camCalculatedValue = camPanPID.calculate(getPanAngle());
    double nonCamCalculatedValue = pidPan.calculate(getPanAngle());
    double speed = usingCamera ? camCalculatedValue : nonCamCalculatedValue;
    double maxSpeed = 0.5;
    setPanSpeed(speed * maxSpeed);
    LogUtils.log("Turret pan angle: " + getPanAngle() + " pan speed: " + speed);
  }

  public void setTiltSpeed(double speed) {
    if (getTiltAngle() <= minRawTilt && speed < 0) {
      speed = 0;
    }
    if (getTiltAngle() >= maxRawTilt && speed > 0) {
      speed = 0;
    }
    SmartDashboard.putNumber("Turret Y Speed", speed);
    speedControllerTilt.set(speed);
  }

  public double getTiltAngle() {
    return speedControllerTilt.getSensorCollection().getAnalogIn(); // subtracting an offset to get angle values back to their original state after replacing sensor
  }

  public double getHoodSpeed() {
    return speedControllerTilt.getSensorCollection().getAnalogInVel();
  }

  public void setTiltTargetAngle(double angle) {
    double mathAngle = angle;
    pidTilt.setSetpoint(mathAngle);
    SmartDashboard.putNumber("Actual Tilt Target", angle);
  }

  public void PIDDriveTilt() { // tilt
    double maxSpeed = 0.4;
    double speed = pidTilt.calculate(getTiltAngle());
    setTiltSpeed(speed * maxSpeed);
    LogUtils.log("Hood angle: " + getTiltAngle() + " hood speed: " + speed);
  }

  public void addTurretSmartDashboard() {
    SmartDashboard.putNumber("Pan Angle", getPanAngle());
    SmartDashboard.putNumber("Pan Angle Raw", speedControllerPan.getSensorCollection().getAnalogInRaw());
    SmartDashboard.putNumber("Tilt Angle", getTiltAngle());
  }

  public void setTurretAngleDashboard() {
    setTiltTargetAngle(SmartDashboard.getNumber("Y Potentiometer, tilt", 0));
    PIDDriveTilt();
  }

  public void smartDashboardConstants() {

    SmartDashboard.putBoolean("Target Aligned", isAtTarget());

    if (tuning) {
      double kXP = SmartDashboard.getNumber("P Gain PAN", 0);
      double kXI = SmartDashboard.getNumber("I Gain PAN", 0);
      double kXD = SmartDashboard.getNumber("D Gain PAN", 0);

      double kYP = SmartDashboard.getNumber("P Gain TILT", 0);
      double kYI = SmartDashboard.getNumber("I Gain TILT", 0);
      double kYD = SmartDashboard.getNumber("D Gain TILT", 0);

      double kCXP = SmartDashboard.getNumber("Camera P Gain PAN", 0);
      double kCXI = SmartDashboard.getNumber("Camera I Gain PAN", 0);
      double kCXD = SmartDashboard.getNumber("Camera D Gain PAN", 0);

      if ((kXP != panP)) {
        pidPan.setP(kXP);
        panP = kXP;
      }
      if ((kXI != panI)) {
        pidPan.setI(kXI);
        panI = kXI;
      }
      if ((kXD != panD)) {
        pidPan.setD(kXD);
        panD = kXD;
      }

      if ((kCXP != camPanP)) {
        camPanPID.setP(kCXP);
        camPanP = kCXP;
      }
      if ((kCXI != camPanI)) {
        camPanPID.setI(kCXI);
        camPanI = kCXI;
      }
      if ((kCXD != camPanD)) {
        camPanPID.setD(kCXD);
        camPanD = kCXD;
      }

      if ((kYP != tiltP)) {
        pidTilt.setP(kYP);
        tiltP = kYP;
      }
      if ((kYI != tiltI)) {
        pidTilt.setI(kYI);
        tiltI = kYI;
      }
      if ((kYD != tiltD)) {
        pidTilt.setD(kYD);
        tiltD = kYD;
      }
    }

  }

  public double getTiltTarget() {
    return pidTilt.getSetpoint();
  }

  public double getPanTarget() {
    return pidPan.getSetpoint();
  }

  private boolean isAtPanTarget() {
    double target = getPanTarget();
    double position = getPanAngle();
    return ((position <= target + panError) && (position >= target - panError));
  }

  private boolean isAtTiltTarget() {
    double target = getTiltTarget();
    double position = getTiltAngle();
    return ((position <= target + tiltError) && (position >= target - tiltError));
  }

  public boolean isAtTarget() {
    return isAtPanTarget() && isAtTiltTarget();
  }

  public void PIDDrive(boolean usingCamera) {
    PIDDrivePan(usingCamera);
    PIDDriveTilt();
  }
}
