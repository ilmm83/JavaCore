package edu.patterns.structure_patterns.adapter;

public class EnemyAttackerApplication {
    public static void main(String[] args) {
        EnemyAttacker tank = new EnemyTank();
        EnemyAttacker robot = new EnemyRobotAdapter(new EnemyRobot());

        doEnemyStaff(tank, "Vasya");
        doEnemyStaff(robot, "Petya");
    }

    private static void doEnemyStaff(EnemyAttacker attacker, String driver) {
        attacker.driveForward();
        attacker.fireWeapon();
        attacker.assignDriver(driver);
        System.out.println();
    }
}
