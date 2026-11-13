// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Subsystems.SwerveSubsystem.Swerve;
import swervelib.SwerveInputStream;
import frc.robot.Subsystems.SpindexerSubsystem.Spindexer;
import frc.robot.Subsystems.SpindexerSubsystem.SpindexerConstants;


public class RobotContainer {

  private final Swerve m_swerve = new Swerve();
  private final Spindexer m_spindexer = new Spindexer();

  private final SendableChooser<Command> autoChooser;

  private final CommandXboxController controller = new CommandXboxController(0);

  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(m_swerve.getSwerveDrive(),
                                                                () -> -controller.getLeftX(), 
                                                                () -> -controller.getLeftY())
                                                                .withControllerRotationAxis(controller::getRightX) // Axis which give the desired angular velocity.
                                                                .deadband(0.05) // Controller deadband
                                                                .scaleTranslation(8.8) // Scaled controller translation axis
                                                                .allianceRelativeControl(true); // Alliance relative controls
  
  SwerveInputStream driveDirectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(()->controller.getRightX(), ()->controller.getRightY());  
  Command driveFieldOrientedDirectAngle = m_swerve.driveFieldOriented(driveDirectAngle);
  Command driveFieldOrientedAngularVelocity = m_swerve.driveFieldOriented(driveAngularVelocity);                                                          


  public RobotContainer() {
    configureBindings();
    m_swerve.setDefaultCommand(driveFieldOrientedAngularVelocity);

    autoChooser = AutoBuilder.buildAutoChooser();
  }

  private void configureBindings() {
      controller.leftBumper().toggleOnTrue(m_spindexer.spindexerCommand());

  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
