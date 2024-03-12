// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  /** Creates a new ClimberSubsystem. */

  private VictorSPX climberMotor;

  public ClimberSubsystem() {
    climberMotor = new VictorSPX(Constants.MotorConstants.CLIMBER_MOTOR_PORT);
    climberMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void climb(double speed) {
    climberMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, speed);
  }
}
