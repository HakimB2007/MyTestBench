// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycle;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlexMotor extends SubsystemBase {
  /** Creates a new FlexMotor. */
  private CANSparkMax motor;
  private RelativeEncoder encoder;
  private SparkPIDController pid;
  public FlexMotor() {
    motor = new CANSparkMax(10, MotorType.kBrushless);
    encoder = motor.getEncoder();
    pid = motor.getPIDController();
    pid.setP(0.01);
    pid.setFeedbackDevice(encoder);
  }
  public void goTo(double setpoint){
    pid.setReference(setpoint, ControlType.kPosition);
  }
  public void stop(){
    motor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Encoder", encoder.getPosition());
  }
}
