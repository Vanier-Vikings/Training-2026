package frc.robot.Subsystems.SwerveSubsystem;

import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.pathplanner.lib.config.PIDConstants;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.util.Units;

public final class SwerveConstants {
    public static final double kPModuleTurningController = 0.5; // Controls how aggressively the module turns toward its target angle.
    public static final double kPModuleDriveController = 0; // Starts at zero; increase later when tuning drive responsiveness.
    public static final double kMaxSpeedMetersPerSecond = 10.0;

    public static final PIDController translationController = new PIDController(0, 0, 0);
    public static final PIDController rotationController = new PIDController(0, 0, 0);
}