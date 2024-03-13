package frc.robot.commands;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsytem;

public class AutonTurnCommand extends Command {

  double time;

  double power = 0.5;

  ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  DriveSubsytem driveSubsytem;

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
      driveSubsytem.drive(0.0, power);
    } else {
      driveSubsytem.drive(0.0, 0.0);
    }
    time--;
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return time <= 0;
  }
}
