// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */

  double point1PowerSpeaker = 0.2;
  double point1DistanceSpeaker = 2;

  double point2PowerSpeaker = 0.3;
  double point2DistanceSpeaker = 3;
  
  double point1PowerAmp = 0.2;
  double point1DistanceAmp = 2;

  double point2PowerAmp = 0.3;
  double point2DistanceAmp = 3;
  

  double point3Power;

  double ratio;

  private final CANSparkMax leftShooterMotor;
  private final CANSparkMax rightShooterMotor;

  public ShooterSubsystem() {
    leftShooterMotor = new CANSparkMax(Constants.MotorConstants.LEFT_SHOOTER_MOTOR_PORT, MotorType.kBrushed);
    rightShooterMotor = new CANSparkMax(Constants.MotorConstants.RIGHT_SHOOTER_MOTOR_PORT, MotorType.kBrushed);
    leftShooterMotor.setInverted(true);
    rightShooterMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shooter(double leftSpeed, double rightSpeed) {
    leftShooterMotor.set(leftSpeed);
    rightShooterMotor.set(rightSpeed);
  }

  public void speakerAutoShoot(double distance) {
    leftShooterMotor.set(speakerLinearInterpolation(distance));
    rightShooterMotor.set(speakerLinearInterpolation(distance));
  }
  public void ampAutoShoot(double distance) {
    leftShooterMotor.set(ampLinearInterpolation(distance));
    rightShooterMotor.set(ampLinearInterpolation(distance));
  }

  public double speakerLinearInterpolation(double distance) {
    return (distance / (point2DistanceSpeaker - point1DistanceSpeaker)) * (point2PowerSpeaker - point1PowerSpeaker);
  }

  public double ampLinearInterpolation(double distance) {
    return (distance / (point2DistanceAmp - point1DistanceAmp)) * (point2PowerAmp - point1PowerAmp);
  }

  public void shoot(double power) {
    leftShooterMotor.set(power);
    rightShooterMotor.set(power);
  }

  public void stop() {
    leftShooterMotor.set(0.0);
    rightShooterMotor.set(0.0);
  }
}
