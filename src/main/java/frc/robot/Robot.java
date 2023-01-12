// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.Automonus;
import static frc.robot.Constants.accelerometer;
import static frc.robot.Constants.driveTrain;
import static frc.robot.Constants.kCameraAuto;
import static frc.robot.Constants.kGyroAuto;
import static frc.robot.Constants.kTimedAuto;
import static frc.robot.Constants.stick;
import static frc.robot.Constants.teleop;
import static frc.robot.Constants.x_default_double;
import static frc.robot.Constants.y_default_double;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Timer Auto", kTimedAuto);
    m_chooser.addOption("Gyro Auto", kGyroAuto);
    m_chooser.addOption("Camera Auto", kCameraAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    driveTrain.arcadeDrive(stick.getZ(), stick.getY());
    double y_accel = accelerometer.getY();
    double x_accel = accelerometer.getX();
    SmartDashboard.putNumber("Y Accel: ", y_accel);
    SmartDashboard.putNumber("x Accel: ", x_accel);
    SmartDashboard.updateValues();
    Event_Listener Events = new Event_Listener();
    NetworkTableInstance inst = NetworkTableInstance.getDefault();

    NetworkTable datatable = inst.getTable("Vision");
    NetworkTableEntry test_Entry = datatable.getEntry("test");
    
    inst.startClientTeam(7035);
    double test = test_Entry.getDouble(0);
    System.out.println(test);
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kGyroAuto:
        Automonus.Gyro();
        break;
      case kCameraAuto:
        Automonus.Camera();
        break;
      case kTimedAuto:
      default:
        Automonus.Timed();
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    teleop.drive();
    teleop.button1();
    teleop.button2();
    teleop.button3();
    teleop.button4();
    teleop.button5();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    double y_accel = accelerometer.getY();
    double x_accel = accelerometer.getX();
    driveTrain.arcadeDrive(y_accel*100, -x_accel*100);
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
