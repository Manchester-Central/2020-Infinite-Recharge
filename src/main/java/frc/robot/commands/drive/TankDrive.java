/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.OI;
import frc.robot.Robot.RobotType;

public class TankDrive extends CommandBase {
  public TankDrive() {
    addRequirements(Robot.driveBase);

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  double speedScale, hardwareScale;

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    speedScale = Robot.oi.getTankDriveSpeedScale();
    hardwareScale = 1;
    if (Robot.hardware == Robot.RobotType.chaos2020) {
      hardwareScale = 1;
    }
    if (Robot.hardware == Robot.RobotType.chaos2019) {
      hardwareScale = 0.3;
    }
    double leftSpeed = Robot.oi.getLeftSpeed() * speedScale * hardwareScale;
    double rightSpeed = Robot.oi.getRightSpeed() * speedScale * hardwareScale;
    // SmartDashboard.putNumber("Left Speed", leftSpeed); TODO: re-enable 
    // SmartDashboard.putNumber("Right Speed", rightSpeed); TODO: re-enable
    Robot.driveBase.tankDriveVolts(leftSpeed, rightSpeed);
    //Robot.driveBase.differentialDrive1.tankDrive(leftSpeed, rightSpeed);
    // Robot.driveBase.differentialDrive1.curvatureDrive(leftSpeed,
    // Robot.oi.driver.getRawAxis(2),
    // Robot.oi.driver.getRawButton(OI.RIGHT_BUMPER));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
