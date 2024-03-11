// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsytem;

public class TankDriveCommand extends Command {
  /** Creates a new tankDriveCommand. */

  DriveSubsytem driveSubsytem;

  double driveMultiplier = 0.8;
  double turnMultiplier = 0.55;
  XboxController controller;

  boolean turbo;

  public TankDriveCommand(DriveSubsytem driveSubsytem, XboxController c, boolean turbo) {
    this.driveSubsytem = driveSubsytem;
    this.controller = c;
    this.turbo = turbo;
    addRequirements(driveSubsytem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(turbo) {
      driveMultiplier = 1;
    }


    double p = controller.getLeftY() * driveMultiplier * -1.0;
    double t = controller.getLeftX() * turnMultiplier * -1.0; 

    SmartDashboard.putNumber("drive power", p);
    SmartDashboard.putNumber("drive turn", t);


    driveSubsytem.drive(p, t);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsytem.drive(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
