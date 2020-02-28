/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climbtake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ManualClimbtake extends CommandBase {
  /**
   * Creates a new FindLimitClimbtake.
   */
  private boolean seenLimit;

  public ManualClimbtake() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.climbTake);
    seenLimit = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    seenLimit = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (!seenLimit) {
      Robot.climbTake.goToLimit();
      seenLimit = Robot.climbTake.getLimitSwitchState();
    } else {
     Robot.climbTake.setPivotSpeed(0);
    }
    Robot.climbTake.setExtenderSpeed(Robot.oi.manualTestExtend());
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
}
