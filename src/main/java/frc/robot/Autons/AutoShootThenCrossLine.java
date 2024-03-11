// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autons;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveSubsytem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShootThenCrossLine extends SequentialCommandGroup {
  public AutoShootThenCrossLine(DriveSubsytem drive, ShooterSubsystem shooter, IndexerSubsystem index) {
    addCommands(
      new ParallelRaceGroup(
        // start shooter
        new RunCommand(()->shooter.shoot(1.0), shooter),

        // wait 2 seconds then run indexer to feed shooter, after 3 seconds, this ends which ends the parallel race group
        new SequentialCommandGroup(
          new WaitCommand(2.0),
          new RunCommand(()->index.index(1.0), index).withTimeout(3.0)
        )
      ),

      // drive back and turn as you drive
      new RunCommand(()->drive.drive(-0.4, 0.0), drive).withTimeout(3.0)
    );
  }
}
