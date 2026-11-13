
package frc.robot.Subsystems.SwerveSubsystem;

import java.io.File;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class Swerve extends SubsystemBase {

    private static final double maxSpeed = Units.feetToMeters(4.5);

    public static final File swerveJsonDirectory =
        new File(Filesystem.getDeployDirectory(), "swerve-config");

    private final SwerveDrive swerveDrive;

    public Swerve() {

        SmartDashboard.putData(this);

        // Configure telemetry
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;

        try {

            swerveDrive = new SwerveParser(swerveJsonDirectory)
                .createSwerveDrive(
                    maxSpeed,
                    new Pose2d(3, 3, Rotation2d.kZero)
                );

        } catch (Exception e) {

            throw new RuntimeException("Failed to initialize SwerveDrive", e);

        }
    }

    public SwerveDrive getSwerveDrive() {
        return swerveDrive;
    }
}
