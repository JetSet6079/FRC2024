// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.betaLib.PidConfig;
import frc.robot.subsystems.DriveSubsytem;

public class AutonDriveCommand extends Command {

  private final DriveSubsytem driveSubsytem;

  private final PidConfig pidConfig = new PidConfig("AutonDrive", 0, true);

  double setPoint, currentPos, power, relativeAngle;

  boolean relative;

  public AutonDriveCommand(DriveSubsytem drive, double setPoint, boolean relative) {
    this.setPoint = setPoint;
    this.driveSubsytem = drive;
    this.relative = relative;
  }

  @Override
  public void initialize() {
    currentPos = driveSubsytem.getLeftPos();

    if (relative) {
      relativeAngle = driveSubsytem.getLeftPos();
    }
  }

  @Override
  public void execute() {

    pidConfig.updateFromSmartDashboard();

    if (relative) {
      currentPos = currentPos - relativeAngle;
    }
    power = currentPos - setPoint * pidConfig.getKd();

    driveSubsytem.drive(power, 0);
    //new TankDriveCommand(driveSubsytem, leftPower, rightPower);
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsytem.drive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
