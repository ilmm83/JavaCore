package edu.patterns.structure_patterns.adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnemyRobotAdapter implements EnemyAttacker {

    private EnemyRobot robot;


    @Override
    public void fireWeapon() {
        robot.smashWithHands();
    }

    @Override
    public void driveForward() {
        robot.walkForward();
    }

    @Override
    public void assignDriver(String driver) {
        robot.reactToHuman(driver);
    }
}
