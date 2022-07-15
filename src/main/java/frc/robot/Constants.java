// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class AutoConstants {

        public static final double kMaxSpeedMetersPerSecond = 1;
        public static final double kMaxVelocityMetersPerSecond = 4.5;  // TODO: Change placeholders
        public static final double kMaxAccelerationMetersPerSecondSquared = 2.5;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 1.25;
        public static final double kPYController = 1.25;
        public static final double kPThetaController = 3;
    
}

public static final class DriveConstants {

    // Distance between centers of right and left wheels on robot
    public static final double kTrackWidth = 0.57785;
    // Distance between front and back wheels on robot
    public static final double kWheelBase = 0.57785;

    public static final SwerveDriveKinematics kDriveKinematics =
    
    new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2)
    );

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

