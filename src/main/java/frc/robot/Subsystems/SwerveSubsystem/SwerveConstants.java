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

    public static class ConfigConstants{
        public static final Pose2d startingPose = new Pose2d(0, 0, Rotation2d.kZero);
    }
}