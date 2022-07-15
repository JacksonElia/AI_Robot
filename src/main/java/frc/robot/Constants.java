// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final class AutoConstants {

    public static final double kMaxSpeedMetersPerSecond = 1;
    public static final double kMaxVelocityMetersPerSecond = 4.5; // TODO: Change placeholders
    public static final double kMaxAccelerationMetersPerSecondSquared = 2.5;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1.25;
    public static final double kPYController = 1.25;
    public static final double kPThetaController = 3;

    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
        kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);

  }

  public static final class DriveConstants {

    public static final int kFrontLeftDriveMotorPort = 18;
    public static final int kRearLeftDriveMotorPort = 6;
    public static final int kFrontRightDriveMotorPort = 4;
    public static final int kRearRightDriveMotorPort = 23;

    public static final int kFrontLeftTurningMotorPort = 1;
    public static final int kRearLeftTurningMotorPort = 7;
    public static final int kFrontRightTurningMotorPort = 3;
    public static final int kRearRightTurningMotorPort = 25;

    public static final int kFrontLeftTurningEncoderPort = 22;
    public static final int kRearLeftTurningEncoderPort = 10;
    public static final int kFrontRightTurningEncoderPort = 9;
    public static final int kRearRightTurningEncoderPort = 8;

    public static final double kFrontLeftAngleZero = 79.45;
    public static final double kRearLeftAngleZero = 121.38;
    public static final double kFrontRightAngleZero = -104.68;
    public static final double kRearRightAngleZero = 23.54;

    public static final boolean kFrontLeftTurningEncoderReversed = false;
    public static final boolean kRearLeftTurningEncoderReversed = false;
    public static final boolean kFrontRightTurningEncoderReversed = false;
    public static final boolean kRearRightTurningEncoderReversed = false;

    public static final boolean kFrontLeftDriveEncoderReversed = false;
    public static final boolean kRearLeftDriveEncoderReversed = false;
    public static final boolean kFrontRightDriveEncoderReversed = true;
    public static final boolean kRearRightDriveEncoderReversed = true;

    public static final double kTrackWidth = 0.57785;
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = 0.57785;
    // Distance between front and back wheels on robot
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    public static final boolean kGyroReversed = false;

    // Values to scale joystick inputs to desired states.
    public static final double kMaxSpeedMetersPerSecond = 4.5; // LOCKED IN
    public static final double kMaxRotationalSpeed = 3 * Math.PI;

    public static final double ksVolts = 0.73394;
    public static final double kvVoltSecondsPerMeter = 2.4068;
    public static final double kaVoltSecondsSquaredPerMeter = 0.28749;

    public static final double ksTurning = 0.77; // LOCKED IN! ----- old 0.66202
    public static final double kvTurning = 0.75; // 0.75 // 3.0052
    public static final double kaTurning = 0; // Default to zero
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI * 2;

  }

  public static final class ModuleConstants {

    public static final double kDriveGearRatio = 7.13;

    public static final double kPModuleTurnController = 8.1; // 8.3 // TUNE: 8.2142
    public static final double kIModuleTurnController = 0; // DO NOT USE
    public static final double kDModuleTurnController = 0; // TUNE

    // Acceleration could be 8pi to make module get anywhere in 0.5 seconds.
    // Will never reach max velocity, so it can be right at the "top" of the
    // triangle.
    // In this case, that would be 2pi.

    public static final double kMaxModuleAngularSpeedRadiansPerSecond = 3 * Math.PI;
    public static final double kMaxModuleAngularAccelerationRadiansPerSecondSquared = 6 * Math.PI;

    public static final double kPModuleDriveController = 1; // TUNE
    public static final double kIModuleDriveController = 0; // DO NOT USE
    public static final double kDModuleDriveController = 0;

    public static final int kDriveFXEncoderCPR = 2048;
    public static final int kTurningCANcoderCPR = 4096;
    public static final double kWheelDiameterMeters = 0.1016; // 4 inches
    public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI; // C = D * pi
    public static final double kDrivetoMetersPerSecond = (10 * kWheelCircumferenceMeters) / (kDriveGearRatio * 2048);
  }

  public static final class AIRobotConstants {

    public static final double cargoDiameterMeters = .2413;

    // This table is used for getting the distance of cargo from the robot
    public static final double[][] heightDistanceTable = {
        // {pxHeight, distance}
        { 0.0, 0.0 },
        { 0.0, 0.0 }
    };

  }

}
