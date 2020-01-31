/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;
import frc.robot.subsystems.NavX;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.TurnAnglePID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;

public class NavXTurnRobot extends Command {
  public NavXTurnRobot() {
    // requires(Robot.driveBase);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  boolean rightJoystickClicked;
  NavX navx;
  CommandGroup commandList;
  // Joystick rightJoy;
  // TurnAnglePID turnAngle;

 public void turnRobotJoystick() {
  //  if (rightJoy.getTriggerPressed()) {

    System.out.println("Right joystick button is clicked");
    // double rightJoystickAngle = rightJoy.getDirectionDegrees();


    // commandList.addSequential(new TurnAnglePID(rightJoystickAngle));
    commandList.addSequential(new TurnAnglePID(navx.getNavAngle()));

  //  }
 }



  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    navx = new NavX();
    commandList = new CommandGroup();
    // rightJoy = new Joystick(12);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    rightJoystickClicked = Robot.oi.driver.getRawButton(12);
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
