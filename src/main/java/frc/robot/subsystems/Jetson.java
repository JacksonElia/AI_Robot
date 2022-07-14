// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.List;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AutoConstants;

public class Jetson extends SubsystemBase {

  private static final Gyro navX = new AHRS(SPI.Port.kMXP);

  /** Creates a new Jetson. */
  public Jetson() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public Trajectory generateTrajectory(double x, double y, double rotation) {

    double curX = 0;
    double curY = 0;

    TrajectoryConfig config = new TrajectoryConfig(
        AutoConstants.kMaxVelocityMetersPerSecond,
        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            // .setKinematics(SwerveDriveConstants.kDriveKinematics)
            .setStartVelocity(0)
            .setEndVelocity(0);

    // TODO: BET $20 over whether initial pose needs to be current pos or 0
    // Lori gets $20 if initi pose is current pos
    Trajectory trajectory = TrajectoryGenerator
        .generateTrajectory(new Pose2d(curX, curY, new Rotation2d(0)),
            List.of(), new Pose2d(curX + x, curY + y, new Rotation2d(rotation)), config);
          
    return trajectory;
  }

}
