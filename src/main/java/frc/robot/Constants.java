// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static class MotorConstants {
    public static final int DRIVE_MOTOR_1_PORT = 10;
    public static final int DRIVE_MOTOR_2_PORT = 2;
    public static final int DRIVE_MOTOR_3_PORT = 3;
    public static final int DRIVE_MOTOR_4_PORT = 4;

    public static final double DRIVE_MAX_SPEED = 0.85;

    public static final int INTAKE_MOTOR_PORT = 7;
    public static final int INDEXER_MOTOR_PORT = 9;
    public static final int LEFT_SHOOTER_MOTOR_PORT = 8;
    public static final int RIGHT_SHOOTER_MOTOR_PORT = 6;

    public static final int CLIMBER_MOTOR_PORT = 5;
  }
  public static class TuningConstants {
    public static final boolean AUTO_DRIVE_TUNABLE = true;
    public static final boolean AUTO_TURN_TUNABLE = true;
    public static final boolean AUTO_ALIGN_TUNABLE = true;
  }
  public static class SenserConstants {
    public static final double CAMERA_HIGHT_INCHES = 30.0;
    public static final double CAMERA_PITCH_DEGREES = 20.0;
    public static final double KNOWN_TAG_SIZE_INCHES = 6.5;
  }
  public static class FieldConstants {
    public static final double SPEAKER_TAG_HIGHT_INCHES = 57.13;
    public static final double AMP_TAG_HIGHT_INCHES = 53.63;

    public static final double METERS_IN_TIME_QUARTERSPEED = 0.0;
    public static final double DEGREES_IN_TIME_QUARTERSPEED = 195.0;
  }
}
