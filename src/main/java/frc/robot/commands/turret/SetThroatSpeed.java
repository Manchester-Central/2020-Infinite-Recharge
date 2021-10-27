/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.util.DoneCommand;
import frc.robot.util.LogUtils;

public class SetThroatSpeed extends DoneCommand {
  /**
   * Creates a new SetThroatSpeed.
   */

  boolean on;

  public SetThroatSpeed(boolean on) {
    addRequirements(Robot.throat);

    this.on = on;
    // Use addRequirements() here to declare subsystem dependencies.
    //SmartDashboard.putNumber("Throat Min", 300);
  }

  double minThroat = 600;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    Robot.throat.ejectorSpeed(on);
   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public boolean isDone() {
    double currentSpeed = Robot.throat.getThroatSpeed();
   // LogUtils.log("SetThroatSpeed is done");
    return currentSpeed > minThroat;
  }
}
