// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autons;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsytem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SenserSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AmpSideAuton extends SequentialCommandGroup {

  public AmpSideAuton(DriveSubsytem drive, ShooterSubsystem shooter, IndexerSubsystem index, IntakeSubsystem intake, SenserSubsystem sensers) {

    addCommands(
      new ParallelRaceGroup(
        // start shooter
        new RunCommand(()->shooter.shoot(1.0), shooter),

        // wait 2 seconds then run indexer to feed shooter, after 3 seconds, this ends which ends the parallel race group
        new SequentialCommandGroup(
          new WaitCommand(2.0),
          new RunCommand(()->index.index(1.0), index).withTimeout(2.0)
        )
      ),

    
      new RunCommand(()->drive.drive(0.0, 0.5), drive).withTimeout(Constants.calculateDistanceAngle(69)),

      new ParallelRaceGroup(
        new RunCommand(()->drive.drive(-0.5, 0), drive).withTimeout(Constants.calculateDistanceMeters(2.2)),
        new WaitCommand(2.0),
        new ParallelCommandGroup(
          new RunCommand(()-> intake.intake(1.0), intake),
          new RunCommand(()-> index.index(1.0), index)
        ).withTimeout(2.0)
      ),

      new RunCommand(()->drive.drive(0.5, 0), drive).withTimeout(Constants.calculateDistanceMeters(2.2)),
      new RunCommand(()->drive.drive(0.0, -0.5), drive).withTimeout(Constants.calculateDistanceAngle(64.5)),

      new ParallelRaceGroup(
        // start shooter
        new RunCommand(()->shooter.shoot(1.0), shooter),

        // wait 2 seconds then run indexer to feed shooter, after 3 seconds, this ends which ends the parallel race group
        new SequentialCommandGroup(
          new WaitCommand(2.0),
          new RunCommand(()->index.index(1.0), index).withTimeout(3.0)
        )
      )


      // new AutonDriveCommand(drive, Units.inchesToMeters(-9.681)).withTimeout(1.0),
      // new AutonTurnCommand(drive, 68.211).withTimeout(1.5),

      // new ParallelRaceGroup(
      //   new AutonDriveCommand(drive, Units.inchesToMeters(-84.82)),
      //   new WaitCommand(2.0),
      //   new ParallelCommandGroup(
      //     new RunCommand(()-> intake.intake(1.0), intake),
      //     new RunCommand(()-> index.index(1.0), index)
      //   ).withTimeout(5.0)
      // )
    );
  }
}
