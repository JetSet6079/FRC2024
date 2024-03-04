// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsytem;

public class AutoCrossTheLine extends SequentialCommandGroup {
  public AutoCrossTheLine(DriveSubsytem drive) {
    addCommands(
      new RunCommand(()->drive.drive(-0.3, 0.0), drive).withTimeout(3.0),
      new InstantCommand(()->drive.stop(), drive)
    );
  }
}
