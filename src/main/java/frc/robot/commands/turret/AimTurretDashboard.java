/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.commands.util.DoneCommand;

public class AimTurretDashboard extends CommandBase {
  public AimTurretDashboard() {
    addRequirements(Robot.turret);

    SmartDashboard.putNumber("Tilt Turret PID target", 0);
    SmartDashboard.putNumber("Pan Turret PID target", 0);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  private double offsetX;

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.turret.setPanTarget(Robot.turret.getPanAngle());
    Robot.turret.setTiltTargetAngle(Robot.turret.getTiltAngle());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

    Robot.turret.setTiltTargetAngle(SmartDashboard.getNumber("Tilt Turret PID target", 0));

    Robot.turret.setPanTarget(SmartDashboard.getNumber("Pan Turret PID target", 0));

    Robot.turret.PIDDrive();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  public boolean isDone() {
    boolean tilt = Robot.turret.getTiltAngle() == Robot.turret.getTiltTarget();

    double panTarget = Robot.turret.getPanTarget();
    double panCurrent = Robot.turret.getPanAngle();
    boolean pan = ((panTarget - 2) < panCurrent) && (panCurrent < (panTarget + 2));

    return (tilt && pan);
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }
}
