/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.OI;
import frc.robot.Robot.RobotType;

public class TankDrive extends Command {
  public TankDrive() {
    requires(Robot.driveBase);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speedScale = 1;
    if( Robot.hardware == Robot.RobotType.chaos2019) {
    speedScale = 0.3;
    }
    double leftSpeed = -Robot.oi.driver.getRawAxis(1);
    double rightSpeed = -Robot.oi.driver.getRawAxis(3);
    SmartDashboard.putNumber("Left Speed", leftSpeed);
    SmartDashboard.putNumber("Right Speed", rightSpeed);
    Robot.driveBase.differentialDrive1.tankDrive(leftSpeed, rightSpeed);
    //Robot.driveBase.differentialDrive1.curvatureDrive(leftSpeed, Robot.oi.driver.getRawAxis(2), Robot.oi.driver.getRawButton(OI.RIGHT_BUMPER));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
