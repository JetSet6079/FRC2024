// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.betaLib.PidConfig;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsytem;
import frc.robot.subsystems.SenserSubsystem;

public class AutoAlignCommand extends Command {

  double offset = 0.0;
  
  DriveSubsytem driveSubsytem;
  SenserSubsystem senserSubsystem;

  PidConfig pidConfig = new PidConfig("Align", 0, Constants.TuningConstants.AUTO_ALIGN_TUNABLE);
  PIDController pid = new PIDController(pidConfig.getKp(), pidConfig.getKi(), pidConfig.getKd());

  public AutoAlignCommand(DriveSubsytem drive, SenserSubsystem senserSubsystem) {
    this.driveSubsytem = drive;
    this.senserSubsystem = senserSubsystem;
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {

    pidConfig.updateFromSmartDashboard();

    PhotonPipelineResult result = senserSubsystem.camera.getLatestResult();
    if (result.hasTargets()) {
      PhotonTrackedTarget target = result.getBestTarget();
      int hashCode = target.hashCode();
      if (3 <= hashCode && hashCode <= 7) {

        new AutonTurnCommand(driveSubsytem, target.getYaw(), true);

       // new AutonDriveCommand(driveSubsytem, senserSubsystem.distanceFromTag() - offset, senserSubsystem.distanceFromTag() - offset, true);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
