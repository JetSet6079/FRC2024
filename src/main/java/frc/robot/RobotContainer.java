// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Autons.TestAuton;
import frc.robot.commands.AutoCrossTheLine;
import frc.robot.commands.AutoShootThenCrossLine;
import frc.robot.commands.AutonDriveCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.subsystems.DriveSubsytem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SenserSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  
  private final DriveSubsytem m_driveSubsytem = new DriveSubsytem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final SenserSubsystem m_senserSubsystem = new SenserSubsystem();

  private final XboxController m_controller = new XboxController(0);

  //IF REALLY SLOW, EDIT THIS
  private double intakeSpeed = 0.75;

  private double shootTestSpeed;

  private boolean detected = false;

  // Put commands in a dropdown in Smart Dashboard
  private SendableChooser<Command> dropdown = new SendableChooser<>();

  public RobotContainer() {

      dropdown.setDefaultOption("Cross The Line", new AutoCrossTheLine(m_driveSubsytem));
      dropdown.addOption("Another Auton", new AutoShootThenCrossLine(m_driveSubsytem, m_shooterSubsystem, m_indexerSubsystem));

      SmartDashboard.getNumber("ShootTestSpeed", shootTestSpeed);
      SmartDashboard.putNumber("ShootTestSpeed", shootTestSpeed);

      m_driveSubsytem.setDefaultCommand(
        new TankDriveCommand(m_driveSubsytem, m_controller)
      );

      //new AutonDriveCommand(m_driveSubsytem, () -> m_controller.getLeftY, () -> m_controller.getLeftY, true);

      // INTAKE IN (A Button)
      new JoystickButton(m_controller, 1).onTrue(
        new ParallelCommandGroup(
          new RunCommand(()->m_intakeSubsystem.intake(intakeSpeed), m_intakeSubsystem),
          new RunCommand(()->m_indexerSubsystem.index(intakeSpeed), m_indexerSubsystem)
        )
      ).onFalse(
        new SequentialCommandGroup(
          new InstantCommand(()->m_intakeSubsystem.intake(0.0), m_intakeSubsystem),
          new InstantCommand(()->m_indexerSubsystem.index(0.0), m_indexerSubsystem)
        )
      );

      // INTAKE OUT (X Button)
      new JoystickButton(m_controller, 3).onTrue(
        new ParallelCommandGroup(
          new RunCommand(()->m_intakeSubsystem.intake(intakeSpeed * -1.0), m_intakeSubsystem),
          new RunCommand(()->m_indexerSubsystem.index(intakeSpeed * -1.0), m_indexerSubsystem)
        )
      ).onFalse(
        new SequentialCommandGroup(
          new InstantCommand(()->m_intakeSubsystem.intake(0.0), m_intakeSubsystem),
          new InstantCommand(()->m_indexerSubsystem.index(0.0), m_indexerSubsystem)
        )
      );

    //TEST AUTON DRIVE, right bumper sticker
    new JoystickButton(m_controller, 5).onTrue(new AutonDriveCommand(m_driveSubsytem, 1.0, false));


      // if (m_controller.getAButton()) {
        
      // } else if (m_senserSubsystem.limitSwitch.get()) {
      //   m_intakeSubsystem.intake(0.0);
      //   m_indexerSubsystem.index(0.0);
      // }
      //new JoystickButton(m_controller, 1).onTrue(new InstantCommand(() -> detect()));

      // Shoot!!! (Y Button)
      new JoystickButton(m_controller, 4).onTrue(
        new RunCommand(()->m_shooterSubsystem.shoot(1.0), m_shooterSubsystem)
      ).onFalse(
        new InstantCommand(()->m_shooterSubsystem.stop(), m_shooterSubsystem)
      );

      // if (m_controller.getYButtonPressed()) {

      //   if (m_senserSubsystem.isSpeaker()) {
      //     m_shooterSubsystem.speakerAutoShoot(m_senserSubsystem.distanceFromTag());
      //   } else {
      //     m_shooterSubsystem.ampLinearInterpolation(m_senserSubsystem.distanceFromTag());
      //   }
        
      //   new WaitCommand(0.2);
      //   m_indexerSubsystem.index(1.0);
      // }
      
      // m_shooterSubsystem.shooter(m_controller.getLeftTriggerAxis(),m_controller.getLeftTriggerAxis());

      // SmartDashboard.putBoolean("detecting", detected);

  }

  public Command getAutonomousCommand() {
    return dropdown.getSelected();
  }

  public void detect() {
    detected = true;
  }
}