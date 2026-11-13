package frc.robot.Subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.IntakeSubsystem.IntakeConstants;
import frc.robot.Subsystems.IntakeSubsystem.IntakeConstants;

public class Intake extends SubsystemBase {
    
    private final SparkMax pivotMotor;
    private final TalonFX rollerMotor;
    private final CANcoder pivotEncoder;
    private final double pivotOffset;
    private final double homeAngle;
    private final double deployedAngle;
    private final double startingPosAngle;  
    private final double rollerRPM;
    private final double rollerSlow;
    private final double rollerOutake;  
    private final int pivotMotorID;
    private final int rollerMotorID;

    public Intake(){

        pivotMotor = new SparkMax(IntakeConstants.pivotMotorID, .kBrushless);
        rollerMotor = new TalonFX(IntakeConstants.pivotMotorID,motorType.kBrushless);
        SparkMaxConfig pivotConfig = new SparkMaxConfig();
            .smartCurrentlimit()




    }    

    }
}
