package frc.robot.Subsystems.SwerveSubsystem;
import java.io.File;
import java.io.ObjectInputFilter.Config;

import com.reduxrobotics.sensors.canandgyro.Canandgyro;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
import swervelib.SwerveDrive;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import yams.mechanisms.config.SwerveDriveConfig;
import yams.telemetry.SwerveDriveTelemetryConfig;
import yams.telemetry.SwerveInputStreamTelemetry;

import frc.robot.Subsystems.SwerveSubsystem.SwerveConstants.ConfigConstants;;

public class Swerve extends SubsystemBase {
    double maximumSpeed = Units.feetToMeters(4.5);
    File directory = new File(Filesystem.getDeployDirectory(), "swerve-config");
    SwerveConstants constants = new SwerveConstants();
      private SwerveDrive                   drive;
      private Canandgyro                    gyro;

    public Swerve(){
        var cfg = new SwerveDriveConfig()
        .withStartingPose(ConfigConstants.startingPose);
        .withSubsystem(this)
        .withTranslationController(new PIDController(4, 0, 0))
        // YAMS stops any module asked for less than 0.1 m/s (its default module velocity deadband), so a weak
        // rotation gain leaves heading control and driveToPoseYAMS short of their target. At kP = 3 that dead zone
        // is under 4.5 degrees.
        .withRotationController(new PIDController(3, 0, 0))
        .withTelemetry("swerve", new SwerveDriveTelemetryConfig()); //.withTelemetryVerbosity(TelemetryVerbosity.HIGH)??

    SwerveParser.parse(new File(Filesystem.getDeployDirectory(), "swerve/base"));
    SwerveDriveDevices<SwerveDrive> devices = SwerveParser.createSwerveDriveDevices(cfg);
    drive = devices.swerveDrive();
    gyro = (Canandgyro) devices.gyro();
    // You can also create the SwerveDrive without the ability to retrieve the devices like this.
    drive = SwerveParser.createSwerveDrive(cfg);

    }
    
}
