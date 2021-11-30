package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.PrintCommand;

import static frc.robot.Constants.ShooterConstants.*;

public class PIDSub extends PIDSubsystem {

    private final TalonSRX m_motor1 = new WPI_TalonSRX(24);
    private final ArmFeedforward m_feedForward = new ArmFeedforward(kS, kCos, kV, kA);

    private double setpoint = 0;

    public PIDSub() {
        super(new PIDController(kP, kI, kD));
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

    private void setMotor(double Output) {
        m_motor1.set(ControlMode.Current, Output);
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
