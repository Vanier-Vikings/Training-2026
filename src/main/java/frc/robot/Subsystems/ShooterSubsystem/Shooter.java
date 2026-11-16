package frc.robot.Subsystems.ShooterSubsystem;

import frc.robot.Subsystems.ShooterSubsystem.ShooterConstants;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.can.MotControllerJNI;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

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
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.util.StatusLogger;



public class Shooter {
    private final SparkMax feederMotor = new SparkMax(ShooterConstants.feederMotorID, SparkLowLevel.MotorType.kBrushless);
    private SparkMaxConfig feederConfig; 
    private final AbsoluteEncoder feederEncoder;
    private SparkClosedLoopController feederPID; 
    //use to run feeder with velocity control
    public final VelocityVoltage m_request = new VelocityVoltage(null);

     private final TalonFX hoodMotor = new TalonFX(1, new CANBus("canivore"));
     private final TalonFXConfiguration mConfig;
     private final CurrentLimitsConfigs mCurrent; 


     public Shooter() {
        
            feederEncoder = feederMotor.getAbsoluteEncoder();
            feederConfig = new SparkMaxConfig(); 

                feederConfig.encoder
                    .velocityConversionFactor(1);

                feederConfig.closedLoop 
                //velocity control
                    .feedbackSensor(FeedbackSensor.kAbsoluteEncoder)
                    .p(ShooterConstants.kFeederP)
                    .i(ShooterConstants.kFeederI)
                    .d(ShooterConstants.kFeederD);
                    


                feederConfig
                    .smartCurrentLimit(40)
                    .inverted(false)
                    .idleMode(IdleMode.kBrake);
  
            feederMotor.configure(feederConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
            
                SmartDashboard.setDefaultNumber("Shooter RPS", ShooterConstants.targetRPS);
                SmartDashboard.setDefaultNumber("Target Voltage", ShooterConstants.targetV);
                SmartDashboard.setDefaultBoolean("Control Mode", false);
                SmartDashboard.setDefaultBoolean("Reset Encoder", false);

           
           
                mConfig = new TalonFXConfiguration();
            mConfig.Slot0.kP = ShooterConstants.kHoodP;
            mConfig.Slot1.kI = ShooterConstants.kHoodI;
            mConfig.Slot2.kD = ShooterConstants.kHoodD;

            //set current limit to 40 Amps
            mCurrent = new CurrentLimitsConfigs();
            mCurrent.SupplyCurrentLimit = ShooterConstants.hoodCurrentLimit;
            
            hoodMotor.getConfigurator(); 

     }

     public void stopShooter() {
            feederMotor.setVoltage(0);
            hoodMotor.setControl(m_request.withVelocity(0));
     }

     public void runShooter() {
            feederMotor.setVoltage(ShooterConstants.targetV);
            hoodMotor.setControl(m_request.withVelocity(ShooterConstants.targetRPS));
     }

     public Command ShooterCommand() {
           return this.startEnd(() -> runShooter(), () -> stopShooter());
     }
}
