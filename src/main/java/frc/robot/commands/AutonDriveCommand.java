// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsytem;

public class AutonDriveCommand extends Command {

  private final DriveSubsytem driveSubsytem;

  //private final PidConfig pidConfig = new PidConfig("AutonDrive", 0, true);

  double time;
  double power = -0.5;

  boolean isFinished;

  public AutonDriveCommand(DriveSubsytem drive, double time) {
    this.time = time;
    this.driveSubsytem = drive;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    //pidConfig.updateFromSmartDashboard();

    if (time > 0) {
      driveSubsytem.drive(power, 0);
    } else {
      driveSubsytem.drive(0, 0);
      isFinished = true;
    }
    time--;

    SmartDashboard.putNumber("time", time);

    //new TankDriveCommand(driveSubsytem, leftPower, rightPower);
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsytem.drive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
