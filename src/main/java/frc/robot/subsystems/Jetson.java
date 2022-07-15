// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.List;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AIRobotConstants;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;

public class Jetson extends SubsystemBase {

  private static final Gyro m_gyro = new AHRS(SPI.Port.kMXP);
  private static final SwerveDriveOdometry m_odometry = new SwerveDriveOdometry(DriveConstants.kDriveKinematics,
      m_gyro.getRotation2d());

  /** Creates a new Jetson object */
  public Jetson() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Generates a trajectory for the robot to follow based on coordinates relative
   * to the robot.
   * 
   * @param x        the x offset in meters relative to the robot.
   * @param y        the y offset in meters relative to the robot.
   * @param rotation the angle in radians of the offset of the ball from the
   *                 center of the camera.
   * @return
   */
  public Trajectory generateTrajectory(double x, double y, double rotation) {

    double curX = m_odometry.getPoseMeters().getX();
    double curY = m_odometry.getPoseMeters().getY();

    TrajectoryConfig config = new TrajectoryConfig(
        AutoConstants.kMaxVelocityMetersPerSecond,
        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            // .setKinematics(SwerveDriveConstants.kDriveKinematics)
            .setStartVelocity(0)
            .setEndVelocity(0);

    // TODO: BET $20 over whether initial pose needs to be current pos or 0
    // Lori gets $20 if initial pose is current pos, Jack gets $20 if otherwise
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
        new Pose2d(curX, curY, new Rotation2d(0)),
        List.of(),
        new Pose2d(curX + x, curY + y, new Rotation2d(rotation)),
        config);

    return trajectory;
  }

  public static double getCargoDistance(double cargoPxHeight) {

    double table[][] = AIRobotConstants.heightDistanceTable;

    double lowerHeight = 0;
    double lowerDistance = 0;
    double higherHeight = 0.01; // Shouldn't be necessary but just in case
    double higherDistance = 0;

    double firstPxHeight = table[0][0];
    double secondPxHeight = table[1][0];
    double lastPxHeight = table[table.length - 1][0];
    double secondTolastPxHeight = table[table.length - 2][0];

    double firstDistance = table[0][1];
    double secondDistance = table[1][1];
    double lastDistance = table[table.length - 1][1];
    double secondTolastDistance = table[table.length - 2][1];

    double cargoDistance = 0;

    // Handles if the cargo is less than the table's least distance
    if (cargoPxHeight < firstPxHeight) {
      // Uses slope from first 2 points to get distance
      double slope = (secondDistance - firstDistance) / (secondPxHeight - firstPxHeight);
      cargoDistance = slope * (firstPxHeight - cargoPxHeight) + firstDistance;
    // Handles if the cargo is greater than the table's max distance
    } else if (cargoPxHeight > lastPxHeight) {
      // Uses slope from last 2 points to get distance
      double slope = (lastDistance - secondTolastDistance) / (lastPxHeight - secondTolastPxHeight);
      cargoDistance = slope * (cargoPxHeight - lastPxHeight) + lastDistance;
    } else {
      // Gets the closest values below and above the desired value
      for (int i = 0; i < table.length; i++) {
        // Checks if the height is in between 2 points in the table
        if (table[i][0] <= cargoPxHeight && table[i + 1][0] > cargoPxHeight) {
          lowerHeight = table[i][0];
          lowerDistance = table[i][1];
          higherHeight = table[i + 1][0];
          higherDistance = table[i + 1][1];
          break;
        }
      }

      // Gets slope connecting points
      double slope = (higherDistance - lowerDistance) / (higherHeight - lowerHeight);
      cargoDistance = (slope * (cargoPxHeight - lowerHeight) + lowerDistance);

    }

    return cargoDistance;

  }

  public static double getCargoXPos() {
    return 0.0;
  }

  public static double getCargoYPos() {
    return 0.0;
  }
}
