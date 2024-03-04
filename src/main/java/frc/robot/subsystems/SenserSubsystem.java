// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SenserSubsystem extends SubsystemBase {
  /** Creates a new SenserSubsystem. */

  public DigitalInput limitSwitch = new DigitalInput(1);
  public PhotonCamera camera = new PhotonCamera("photonvision");

  double distance = 0.0;
  double tagHight;

  boolean isSpeaker = true;

  public SenserSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double distanceFromTag() {

    PhotonPipelineResult result = camera.getLatestResult();
    if (result.hasTargets()) {
      PhotonTrackedTarget target = result.getBestTarget();
      int hashCode = target.hashCode();
      if (3 <= hashCode && hashCode <= 7) {

        if (target.hashCode() == 3 || target.hashCode() == 4 || target.hashCode() == 7 || target.hashCode() == 8) {
          tagHight = Constants.FieldConstants.SPEAKER_TAG_HIGHT_INCHES;
        } else if (target.hashCode() == 5 || target.hashCode() == 6) {
         tagHight = Constants.FieldConstants.AMP_TAG_HIGHT_INCHES;
        }

        distance = PhotonUtils.calculateDistanceToTargetMeters(Units.inchesToMeters(Constants.SenserConstants.CAMERA_HIGHT_INCHES), Units.inchesToMeters(tagHight), Units.degreesToRadians(Constants.SenserConstants.CAMERA_PITCH_DEGREES), Units.degreesToRadians(result.getBestTarget().getPitch()));
      }
    }
    return distance;
  }

  public boolean isSpeaker() {
    PhotonPipelineResult result = camera.getLatestResult();
    if (result.hasTargets()) {
      PhotonTrackedTarget target = result.getBestTarget();
      int hashCode = target.hashCode();
      if (3 <= hashCode && hashCode <= 7) {

        if (target.hashCode() == 3 || target.hashCode() == 4 || target.hashCode() == 7 || target.hashCode() == 8) {
          isSpeaker = true;
        } else if (target.hashCode() == 5 || target.hashCode() == 6) {
          isSpeaker = false;
        }
      }
    }
    return isSpeaker;
  }
}
