/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.commands.util.DoneCommand;

public class AimTurret extends DoneCommand {
  public AimTurret() {
    addRequirements(Robot.turret, Robot.flywheel);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  private double targetAngleX, targetAngleY;
  private double offsetX;
  private double tableTargetAngle, tableTargetSpeed;

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.camera.setPipeline(9); // TODO: change!!!
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    if (Robot.camera.hasTarget()) {
      targetAngleY = Robot.camera.getYAngle();
      // add conversion from camera output to setpoint target using table
      Robot.turret.setHoodTargetAngle(tableTargetAngle);
      Robot.flywheel.setTargetSpeed(tableTargetSpeed);

      targetAngleX = Robot.camera.getXAngle();
      Robot.turret.setXTarget(targetAngleX);
      Robot.turret.PIDDrive();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean isDone() {
    return true; // TODO: set true when turret is aligned
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
