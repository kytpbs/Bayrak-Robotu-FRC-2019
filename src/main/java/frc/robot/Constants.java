package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
public final class Constants{
    public static PWMSparkMax leftMotor1 = new PWMSparkMax(0);
    public static PWMSparkMax leftMotor2 = new PWMSparkMax(1);
    public static PWMSparkMax rightMotor1 = new PWMSparkMax(3);
    public static PWMSparkMax rightMotor2 = new PWMSparkMax(4);
    public static MotorControllerGroup rightMotorsGroup = new MotorControllerGroup(rightMotor1,rightMotor2);
    public static MotorControllerGroup leftMotorsGroup  = new MotorControllerGroup(leftMotor1, leftMotor2);
    public static DifferentialDrive driveTrain = new DifferentialDrive(leftMotorsGroup,rightMotorsGroup);

    public static Joystick stick = new Joystick(0);

    public static Accelerometer accelerometer = new BuiltInAccelerometer();
    
}