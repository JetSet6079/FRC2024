// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class DriveSubsytem extends SubsystemBase {

  private CANSparkMax left1Motor;
  private CANSparkMax left2Motor;
  private CANSparkMax right1Motor;
  private CANSparkMax right2Motor;

  private DifferentialDrive drive;

  /** Creates a new DriveSubsytem. */
  public DriveSubsytem() {
    left1Motor = new CANSparkMax(Constants.MotorConstants.DRIVE_MOTOR_1_PORT, MotorType.kBrushless);
    left2Motor = new CANSparkMax(Constants.MotorConstants.DRIVE_MOTOR_2_PORT, MotorType.kBrushless);
    right1Motor = new CANSparkMax(Constants.MotorConstants.DRIVE_MOTOR_3_PORT, MotorType.kBrushless);
    right2Motor = new CANSparkMax(Constants.MotorConstants.DRIVE_MOTOR_4_PORT, MotorType.kBrushless);

    left1Motor.setInverted(false);
    left2Motor.setInverted(false);
    right1Motor.setInverted(true);
    right2Motor.setInverted(true);    

    left2Motor.follow(left1Motor);
    right2Motor.follow(right1Motor);

    drive = new DifferentialDrive(left1Motor, right1Motor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //drive(leftPower, rightPower);
  }

  public void drive(double power, double turn) {
    // MathUtil.clamp(left, -Constants.MotorConstants.DRIVE_MAX_SPEED, Constants.MotorConstants.DRIVE_MAX_SPEED);
    // MathUtil.clamp(right, -Constants.MotorConstants.DRIVE_MAX_SPEED, Constants.MotorConstants.DRIVE_MAX_SPEED);
    //drive.tankDrive(left, right);

    drive.arcadeDrive(power, turn);

    //drive.feed();
  }

  public void stop() {
    drive.arcadeDrive(0.0, 0.0);
  }

  public double getLeftPos() {
    return left1Motor.getEncoder().getPosition();
  }
  public double getRightPos() {
    return right1Motor.getEncoder().getPosition();
  }
}
