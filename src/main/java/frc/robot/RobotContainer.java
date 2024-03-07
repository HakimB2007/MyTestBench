// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FlexMotor;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  FlexMotor motor = new FlexMotor();
  PS5Controller controller = new PS5Controller(0);
  JoystickButton FIRSTSETPOINT = new JoystickButton(controller, PS5Controller.Button.kL2.value);
  JoystickButton SECONDSETPOINT = new JoystickButton(controller, PS5Controller.Button.kR2.value);
  JoystickButton THIRDSETPOINT = new JoystickButton(controller, PS5Controller.Button.kL1.value);
  JoystickButton FOURSETPOINT = new JoystickButton(controller, PS5Controller.Button.kR1.value);
  JoystickButton STOP = new JoystickButton(controller, PS5Controller.Button.kCircle.value);
  JoystickButton BACK = new JoystickButton(controller, PS5Controller.Button.kSquare.value);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the trigger bindings
    configureBindings();
    //MOTOR.whileTrue(new RunCommand(motor.goToPose(0);, motor));
    

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    FIRSTSETPOINT.whileTrue(new RunCommand(() -> motor.goTo(20), motor));
    SECONDSETPOINT.whileTrue(new RunCommand(() -> motor.goTo(40), motor));
    THIRDSETPOINT.whileTrue(new RunCommand(() -> motor.goTo(10), motor));
    FOURSETPOINT.whileTrue(new RunCommand(() -> motor.goTo(-20), motor));
    STOP.whileTrue(new RunCommand(() -> motor.stop(), motor));
    BACK.whileTrue(new RunCommand(() -> motor.goTo(0.5), motor));

    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
