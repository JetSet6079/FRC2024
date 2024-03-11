// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.betaLib.PidConfig;
import frc.robot.subsystems.DriveSubsytem;

public class AutonDriveCommand extends Command {

  private final DriveSubsytem driveSubsytem;

  //private final PidConfig pidConfig = new PidConfig("AutonDrive", 0, true);

  double time;
  double power = 0.1;
  double kp = 0.0001;

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
      time++;
      driveSubsytem.drive(power, 0);
    } else {
      driveSubsytem.drive(0, 0);
      isFinished = true;
    }

    
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
