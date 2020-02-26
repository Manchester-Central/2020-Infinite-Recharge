/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.serializer;

import frc.robot.Robot;
import frc.robot.commands.util.DoneCommand;
import frc.robot.subsystems.serializer.SerializerSpeed;

public class SerializerFeed extends DoneCommand {
  private double distance;
  private double start;

  public SerializerFeed() {
    this(-1);
  }

  public SerializerFeed(double distance) {
    this.distance = distance;
    // Use requires() here to declare subsystem dependencies
    addRequirements(Robot.serializer);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    start = Robot.serializer.getPosition();
    System.out.println("starting" + start);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Robot.serializer.driveTurnTable(SerializerSpeed.fast);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    if (distance < 0) {
      return false;
    }
    return Math.abs(start - Robot.serializer.getPosition()) > distance;
  }

  // Called once after isFinished returns true or when interrupted
  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isDone() {
    // TODO Auto-generated method stub
    return false;
  }

}
