// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems.drivebase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotConstants2020;
import frc.robot.util.LogUtils;

/**
 *
 */
public class DriveBase2020 extends SubsystemBase implements IDriveBase {

    private Victor left1;
    private Victor left2;
    private Victor left3;
    private WPI_TalonSRX left4 = null;
    private SpeedControllerGroup leftDrive;
    private Victor right1;
    private Victor right2;
    private Victor right3;
    private WPI_TalonSRX right4 = null;
    private SpeedControllerGroup rightDrive;
    public DifferentialDriveOdometry odometer;
    private PIDController PIDRight;
    private PIDController PIDLeft;
    private CANSparkMax leftSpark1;
    private CANSparkMax leftSpark2;
    private CANSparkMax rightSpark1;
    private CANSparkMax rightSpark2;
    private Robot.RobotType type;
    private Encoder sim_encoder_l;
    private Encoder sim_encoder_r;
    private double setpointLeft, setpointRight;
    private double kP, kI, kD;
    private int currentLimit;
    public DifferentialDrive differentialDrive1;

    private final double pidDoneAllowedPositionError = 1;
    private final double pidDoneAllowedVelocityError = 1;
    private final double TRACK_WIDTH = 26.5;

    final boolean tuning = false;

    public DriveBase2020() {

        kP = 0.055;
        kI = 0.025;
        kD = 0;
        currentLimit = 60;

        PIDRight = new PIDController(kP, kI, kD);
        PIDLeft = new PIDController(kP, kI, kD);

        leftSpark1 = new CANSparkMax(RobotConstants2020.DRIVE_LEFT_SPARKMAX_A, CANSparkMax.MotorType.kBrushless);
        // addChild("Left1", leftSpark1);
        leftSpark1.setInverted(false);
        leftSpark1.setSmartCurrentLimit(currentLimit);

        leftSpark2 = new CANSparkMax(RobotConstants2020.DRIVE_LEFT_SPARKMAX_B, CANSparkMax.MotorType.kBrushless);
        // addChild("Left2", leftSpark2);
        leftSpark2.setInverted(false);
        leftSpark2.setSmartCurrentLimit(currentLimit);

        rightSpark1 = new CANSparkMax(RobotConstants2020.DRIVE_RIGHT_SPARKMAX_A, CANSparkMax.MotorType.kBrushless);
        // addChild("Right1", rightSpark1);
        rightSpark1.setInverted(false);
        rightSpark1.setSmartCurrentLimit(currentLimit);

        rightSpark2 = new CANSparkMax(RobotConstants2020.DRIVE_RIGHT_SPARKMAX_B, CANSparkMax.MotorType.kBrushless);
        // addChild("Right2", rightSpark2);
        rightSpark2.setInverted(false);
        rightSpark2.setSmartCurrentLimit(currentLimit);

        leftDrive = new SpeedControllerGroup(leftSpark1, leftSpark2);
        rightDrive = new SpeedControllerGroup(rightSpark1, rightSpark2);

        if (tuning) {
            SmartDashboard.putNumber("P Gain Drive", 0);
            SmartDashboard.putNumber("I Gain Drive", 0);
            SmartDashboard.putNumber("D Gain Drive", 0);
        }

        // addChild("LeftDrive", leftDrive);
        // addChild("RightDrive", rightDrive);
        differentialDrive1 = new DifferentialDrive(leftDrive, rightDrive);
        // addChild("Differential Drive 1", differentialDrive1);
        differentialDrive1.setSafetyEnabled(true);
        differentialDrive1.setExpiration(0.1);
        differentialDrive1.setMaxOutput(1.0);
        double navxAngle = Robot.navx.getNavYaw();
        odometer = new DifferentialDriveOdometry(Rotation2d.fromDegrees(navxAngle));

    }

    protected void setup() {
        /*
         * leftSpark1 = new CANSparkMax(RobotConstants2020.DRIVE_LEFT_SPARKMAX_A,
         * CANSparkMax.MotorType.kBrushless); // addChild("Left1", leftSpark1);
         * leftSpark1.setInverted(false);
         *
         * leftSpark2 = new CANSparkMax(RobotConstants2020.DRIVE_LEFT_SPARKMAX_B,
         * CANSparkMax.MotorType.kBrushless); // addChild("Left2", leftSpark2);
         * leftSpark2.setInverted(false);
         *
         * rightSpark1 = new CANSparkMax(RobotConstants2020.DRIVE_RIGHT_SPARKMAX_A,
         * CANSparkMax.MotorType.kBrushless); // addChild("Right1", rightSpark1);
         * rightSpark1.setInverted(false);
         *
         * rightSpark2 = new CANSparkMax(RobotConstants2020.DRIVE_RIGHT_SPARKMAX_B,
         * CANSparkMax.MotorType.kBrushless); // addChild("Right2", rightSpark2);
         * rightSpark2.setInverted(false);
         *
         * leftDrive = new SpeedControllerGroup(leftSpark1, leftSpark2); rightDrive =
         * new SpeedControllerGroup(rightSpark1, rightSpark2);
         */
    }

    protected double encoderInches(WPI_TalonSRX driveInput) {
        if (driveInput == null) {
            return 0;
        }
        double wheelDiameter = 4.0;
        double gearRatio = (double) 1 / 1; // ratio of the axel the wheel lies on to the axel the encoder reads
        int ticksPerRev = 4096; // amount of ticks in one revolution of the encoder axel
        double counts = driveInput.getSensorCollection().getQuadraturePosition();
        double ratio = (gearRatio * wheelDiameter * Math.PI) / ticksPerRev;
        return counts * ratio;
    }

    public double encoderInches(CANSparkMax driveInput) {

        double counts = driveInput.getEncoder().getPosition();
        double ratio = 144 / 69.97808837890625;
        return counts * ratio;
    }

    public double speedToVolts(double speed) {
        double volts = 12;
        double speedFactor = 1;
        return speed * volts * speedFactor;
    }

    public void tankDriveVolts(double leftSpeed, double rightSpeed) {
        // double leftVolts = speedToVolts(leftSpeed);
        // double rightVolts = speedToVolts(rightSpeed);
        // leftDrive.setVoltage(leftVolts);
        // rightDrive.setVoltage(-rightVolts);

        // // differentialDrive1.feed(); commented out from before

        differentialDrive1.tankDrive(leftSpeed, rightSpeed);
    }

    public double angleToDist(double angle) {

        double inchPerRev = 92.45; // constant equal to the total distance the wheels move for one full revolution
        return (inchPerRev * angle) / 360;
    }

    public void reportPosition() {

    }

    public double getRightPosition() {
        return -encoderInches(rightSpark1);
    }

    public double getLeftPosition() {
        return encoderInches(leftSpark1);
    }

    public void PIDDrive() {
        double right = PIDRight.calculate(getRightPosition());
        double left = PIDLeft.calculate(getLeftPosition());
        // double leftSign = left / Math.abs(left);
        // double rightSign = right / Math.abs(right);

        // right = Math.min(maxSpeed, Math.max(minSpeed, Math.abs(right))) * rightSign;
        // left = Math.min(maxSpeed, Math.max(minSpeed, Math.abs(left))) * leftSign;

        // right = isAtRightTarget() ? 0 : right;
        // left = isAtLeftTarget() ? 0 : left;

        differentialDrive1.tankDrive(left, right);

    }

    public PIDController getPIDLeft() {
        return PIDLeft;
    }

    public PIDController getPIDRight() {
        return PIDRight;
    }

    public void setTarget(double left, double right) {
        setpointLeft = left;
        setpointRight = right;
        PIDLeft.setSetpoint(left);
        PIDRight.setSetpoint(right);
    }

    public void setTargetAngle(double targetAngle) {
        double delta = angleToDist(targetAngle);
        double targetLeft = getLeftPosition() + delta;
        double targetRight = getRightPosition() - delta;
        Robot.driveBase.setTarget(targetLeft, targetRight);
        System.out
                .println("setTargetAngle initialized, target left = " + targetLeft + " target right = " + targetRight);
    }

    public boolean isAtTarget() {
        return isAtLeftTarget() && isAtRightTarget();
    }

    public boolean isAtRightTarget() {
        LogUtils.log("Drive/RightPositionErr", PIDRight.getSetpoint() - getRightPosition());
        LogUtils.log("Drive/RightVelocityErr", rightSpark1.getEncoder().getVelocity());

        final boolean AtPosition = (PIDRight.getSetpoint() - getRightPosition()) < pidDoneAllowedPositionError;
        final boolean AtVelocity = rightSpark1.getEncoder().getVelocity() < pidDoneAllowedVelocityError;
        return AtPosition && AtVelocity;
    }

    public boolean isAtLeftTarget() {
        LogUtils.log("Drive/LeftPositionErr", PIDLeft.getPositionError());
        LogUtils.log("Drive/LefttVelocityErr", leftSpark1.getEncoder().getVelocity());

        final boolean AtPosition = PIDLeft.getPositionError() < pidDoneAllowedPositionError;
        final boolean AtVelocity = leftSpark1.getEncoder().getVelocity() < pidDoneAllowedVelocityError;
        return AtPosition && AtVelocity;
    }

    public void resetOdometry() {
        leftSpark1.getEncoder().setPosition(0);
        rightSpark1.getEncoder().setPosition(0);

        double navxAngle = Robot.navx.getNavYaw();
        Rotation2d rotation = Rotation2d.fromDegrees(navxAngle);
        odometer.resetPosition(new Pose2d(0, 0, rotation), rotation);
    }

    public Pose2d getPose() {
        return odometer.getPoseMeters();
    }

    public void resetPosition() {
        rightSpark1.getEncoder().setPosition(0);
        leftSpark1.getEncoder().setPosition(0);
    }

    @Override
    public void periodic() {
        //SmartDashboard.putNumber("Right Encoder", rightSpark1.getEncoder().getPosition());
        //SmartDashboard.putNumber("Left Encoder", -leftSpark1.getEncoder().getPosition());
        // Put code here to be run every loop
        double rightInches = getRightPosition();
        double leftInches = getLeftPosition();
        double navxAngle = Robot.navx.getNavYaw();
        // converts raw encoder readout to inches
        odometer.update(Rotation2d.fromDegrees(navxAngle), leftInches, rightInches);
        SmartDashboard.putNumber("Right Position", rightInches);
        SmartDashboard.putNumber("Left Position", leftInches);

        if (tuning) {
            // read PID coefficients from SmartDashboard
            double p = SmartDashboard.getNumber("P Gain Drive", 0);
            double i = SmartDashboard.getNumber("I Gain Drive", 0);
            double d = SmartDashboard.getNumber("D Gain Drive", 0);

            // if PID coefficients on SmartDashboard have changed, write new values to
            // controller
            if ((p != kP)) {
                PIDRight.setP(p);
                PIDLeft.setP(p);
                kP = p;
            }
            if ((i != kI)) {
                PIDRight.setI(i);
                PIDLeft.setI(i);
                kI = i;
            }
            if ((d != kD)) {
                PIDRight.setD(d);
                PIDLeft.setD(d);
                kD = d;
            }
        }

        // Translation2d translation = odometer.getPoseMeters().getTranslation();

        // SmartDashboard.putNumber("Odometer x", translation.getX()); TODO: potentially
        // re-enable
        // SmartDashboard.putNumber("Odometer y", translation.getY());
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(left4.getSensorCollection().getQuadratureVelocity(),
                right4.getSensorCollection().getQuadratureVelocity()); // TODO: separate and clean up
    }

    @Override
    public SpeedControllerGroup getLeftDrive() {
        return new SpeedControllerGroup(left1, left2, left3, left4);
    }

    @Override
    public SpeedControllerGroup getRightDrive() {
        return new SpeedControllerGroup(right1, right2, right3, right4);
    }

    @Override
    public void tankDrive(double left, double right) {
        // TODO Auto-generated method stub
        differentialDrive1.tankDrive(left, right);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}
