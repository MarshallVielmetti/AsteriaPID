package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.PrintCommand;

import static frc.robot.Constants.ArmConstants.*;

public class PIDSub extends PIDSubsystem {

    private final TalonSRX m_motor1 = new WPI_TalonSRX(24);

    // https://www.chiefdelphi.com/t/velocity-limiting-pid/164908/22
    private final ArmFeedforward m_feedForward = new ArmFeedforward(kS, kCos, kV, kA);

    private double setpoint = 0;

    public PIDSub() {
        super(new PIDController(kP, kI, kD));

        m_motor1.setInverted(true);

        m_motor1.setSelectedSensorPosition(0);

        SmartDashboard.putNumber("Setpoint", setpoint);
        SmartDashboard.putNumber("EncoderPos", m_motor1.getSelectedSensorPosition());

        enable();
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        // TODO Auto-generated method stub
        double ff = m_feedForward.calculate(setpoint, 0); // TODO calc feedforward
        setMotor(output);
    }

    @Override
    protected double getMeasurement() {
        // TODO Auto-generated method stub
        return m_motor1.getSelectedSensorPosition();
    }

    private void setMotor(double output) {

        if (output > 0.4) {
            output = 0.4;
        } else if (output < -0.4) {
            output = -0.4;
        }
        SmartDashboard.putNumber("Output", output);
        m_motor1.set(ControlMode.PercentOutput, output);
    }

    @Override
    public void periodic() {
        super.periodic(); // make sure it's called
        double newSetpoint = SmartDashboard.getNumber("Setpoint", 0);
        SmartDashboard.putNumber("EncoderPos", m_motor1.getSelectedSensorPosition());
        if (this.setpoint != newSetpoint) {
            this.setpoint = newSetpoint;
            this.setSetpoint(this.setpoint);
        }

    }

}
