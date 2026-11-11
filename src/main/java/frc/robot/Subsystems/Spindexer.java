package frc.robot.Subsystems;

import frc.robot.Subsystems.SpindexerConstants;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.util.StatusLogger;

public class Spindexer extends SubsystemBase {
    //Change ids later 
    private static SparkMax spinMax =  new SparkMax(SpindexerConstants.spindexerMotorID, SparkLowLevel.MotorType.kBrushless);
    private SparkMaxConfig mMaxConfig;
    private double m_voltage = SpindexerConstants.m_voltage;

    
    public Spindexer() {
        mMaxConfig = new SparkMaxConfig();
        mMaxConfig
        //set current limit to 40 amps
            .smartCurrentLimit(40)
            .idleMode(IdleMode.kBrake)
            .inverted(false);
        spinMax.configure(mMaxConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);   
        
            SmartDashboard.setDefaultNumber("Target Position", 0);
            SmartDashboard.setDefaultNumber("Target Voltage", m_voltage);
            SmartDashboard.setDefaultBoolean("Control Mode", false);
            
            
    }  

    //using the set voltage that takes a double 
    public void runSpindexer(double voltage) {
        spinMax.setVoltage(voltage);
    }

    public void stopSpindexer() {
        spinMax.setVoltage(0.0);
    }

    public Command mrDoungCommand (double m_voltage) {
        return this.startEnd(() -> runSpindexer(m_voltage), () -> stopSpindexer());
    } 

}
