package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.betaLib.PidConfig;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsytem;

public class AutonTurnCommand extends Command {

  private final PidConfig pidConfig = new PidConfig("AutonTurn", 0, Constants.TuningConstants.AUTO_TURN_TUNABLE);

  double setpoint;
  double currentAngle;

  double relativeAngle;
  boolean relative;

  double pidOutput, ffOutput;

  PIDController pid = new PIDController(pidConfig.getKp(), pidConfig.getKi(), pidConfig.getKd());

  ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  DriveSubsytem driveSubsytem;

  public AutonTurnCommand(DriveSubsytem drive, double setpoint, boolean relative) {
    this.setpoint = setpoint;
    this.driveSubsytem = drive;
    this.relative = relative;
  }

  @Override
  public void initialize() {
    pid.enableContinuousInput(-180, 180);
    pid.setTolerance(1.0);

    relativeAngle = gyro.getAngle();
  }

  @Override
  public void execute() {

    pidConfig.updateFromSmartDashboard();

    if (!relative) {
          currentAngle = gyro.getAngle();
    } else {
      currentAngle = gyro.getAngle() - relativeAngle;
    }

    
    pidOutput = pid.calculate(currentAngle, setpoint);

    //If it's too slow, this is probably why.
    pidOutput = MathUtil.clamp(pidOutput, -0.4, 0.4);

    //new TankDriveCommand(driveSubsytem, pidOutput, -pidOutput);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
  }
}
