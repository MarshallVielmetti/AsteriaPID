package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.ArmFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.ArmConstants.*;

//https://docs.ctre-phoenix.com/en/stable/ch16_ClosedLoop.html#arbitrary-feed-forward
//https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java%20General/PositionClosedLoop/src/main/java/frc/robot/Robot.java

public class TalonPIDSub extends SubsystemBase {
    private final TalonSRX m_motor = new TalonSRX(kMotorID);

    private final ArmFeedforward m_feedForward = new ArmFeedforward(kS, kCos, kV);
    private final TalonSRXPIDSetConfiguration m_pidConfig = new TalonSRXPIDSetConfiguration();

    public TalonPIDSub() {
        /* Factory Default all hardware to prevent unexpected behaviour */
        m_motor.configFactoryDefault();

        /* Config the sensor used for Primary PID and sensor direction */
        m_motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);

        /* Ensure sensor is positive when output is positive */
        m_motor.setSensorPhase(false);

        /**
         * Set based on what direction you want forward/positive to be.
         * This does not affect sensor phase.
         */
        m_motor.setInverted(false);

        /* Config the peak and nominal outputs, 12V means full */
        m_motor.configNominalOutputForward(0, kTimeoutMs);
        m_motor.configNominalOutputReverse(0, kTimeoutMs);
        m_motor.configPeakOutputForward(1, kTimeoutMs);
        m_motor.configPeakOutputReverse(-1, kTimeoutMs);

        /**
         * Config the allowable closed-loop error, Closed-Loop output will be
         * neutral within this range. See Table in Section 17.2.1 for native
         * units per rotation.
         * 0?
         */
        m_motor.configAllowableClosedloopError(0, 0, kTimeoutMs);

        /* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
        m_motor.config_kF(0, kF, kTimeoutMs);
        m_motor.config_kP(0, kP, kTimeoutMs);
        m_motor.config_kI(0, kI, kTimeoutMs);
        m_motor.config_kD(0, kF, kTimeoutMs);
    }

    public void setMotor(double setpoint) {
        double aFF = m_feedForward.calculate(setpoint, 0);

        m_motor.set(ControlMode.MotionMagic, setpoint, DemandType.ArbitraryFeedForward, aFF);
    }

}
