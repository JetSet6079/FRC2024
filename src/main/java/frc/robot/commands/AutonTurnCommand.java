package frc.robot.commands;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsytem;

public class AutonTurnCommand extends Command {

  double time;

  double power = 0.5;

  ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  DriveSubsytem driveSubsytem;

  boolean isFinished = false;

  public AutonTurnCommand(DriveSubsytem drive, double time) {
    this.driveSubsytem = drive;
    this.time = time;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (time > 0) {
      time++;
      driveSubsytem.drive(0.0, power);
    } else {
      isFinished = true;
      driveSubsytem.drive(0.0, 0.0);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
