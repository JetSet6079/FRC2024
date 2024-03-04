// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IndexerSubsystem extends SubsystemBase {
  /** Creates a new IndexerSubsystem. */

  private final CANSparkMax indexerMotor;

  public IndexerSubsystem() {
    indexerMotor = new CANSparkMax(Constants.MotorConstants.INDEXER_MOTOR_PORT, MotorType.kBrushless);
    indexerMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void index(double speed) {
    indexerMotor.set(speed);
  }
}
