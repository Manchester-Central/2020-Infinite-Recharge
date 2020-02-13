/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class DriveSquare extends ParallelCommandGroup {
  /**
   * Add your docs here.
   */
  public DriveSquare(double side) {
    addCommands(new DriveDistancePID(side), new TurnAnglePID(-90), new DriveDistancePID(side), new TurnAnglePID(-90),
        new DriveDistancePID(side), new TurnAnglePID(-90), new DriveDistancePID(side), new TurnAnglePID(-90));
    // Add Commands here:
    // e.g. new Command1());
    // new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
