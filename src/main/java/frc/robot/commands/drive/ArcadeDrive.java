// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ArcadeDrive extends CommandBase {

  private double m_powerScale = 1.0;

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive() {    
    addRequirements(Robot.driveBase);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  /**
   * Updates the power scale 
   * @param powerScale
   */
  public void updatePowerScale(double powerScale) {
    m_powerScale = powerScale;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.driveBase.arcadeDrive(Robot.oi.driver.getLeftY() * m_powerScale, Robot.oi.driver.getRightX() * m_powerScale, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
