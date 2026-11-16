package frc.robot.Subsystems.ShooterSubsystem;

import frc.robot.Subsystems.ShooterSubsystem.ShooterConstants;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.thrifty.core.MotorConfig;
import com.revrobotics.config.BaseConfig;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;;
import com.revrobotics.util.StatusLogger;


public class Shooter {
    private final TalonFX hoodMotor;
    private final SparkMax feederMotor;

    private final TalonFXConfiguration hoodConfig;
    private final SparkMaxConfig feederConfig;

    public Shooter(){
        hoodMotor.getConfigurator().apply(hoodConfig);
        hoodMotor.setPosition(0);

        hoodMotor = new TalonFX(ShooterConstants.hoodMotorID);
        feederMotor = new SparkMax(ShooterConstants.feederMotorID, MotorType.kBrushless);

        hoodConfig = new TalonFXConfiguration();
        hoodConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        hoodConfig.MotionMagic.MotionMagicCruiseVelocity = ShooterConstants.hoodMotorCruiseVelocity;
        hoodConfig.MotionMagic.MotionMagicAcceleration = ShooterConstants.hoodMotorAcceleration;
        hoodConfig.Slot0.kS = ShooterConstants.kHoodS;
        hoodConfig.Slot0.kA = ShooterConstants.kHoodA;
        hoodConfig.Slot0.kV = ShooterConstants.kHoodV;
        hoodConfig.Slot0.kG = ShooterConstants.kHoodG;
        hoodConfig.Slot0.kP = ShooterConstants.kHoodP;
        hoodConfig.Slot0.kI = ShooterConstants.kHoodI;
        hoodConfig.Slot0.kD = ShooterConstants.kHoodD;

        hoodMotor.getConfigurator().apply(hoodConfig);
        hoodMotor.setPosition(0);

        feederConfig = new SparkMaxConfig();
        feederConfig
        .smartCurrentLimit(ShooterConstants.feederCurrentStallLimit)
        .idleMode(IdleMode.kBrake)
        .inverted(false);

        feederMotor.configure(feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
}
