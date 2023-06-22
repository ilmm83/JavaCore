package edu.patterns.structure_patterns.adapter;

import java.util.Random;

public class EnemyTank implements EnemyAttacker {

    private Random generator = new Random();


    @Override
    public void fireWeapon() {
        var damage = generator.nextInt(10) + 1;
        System.out.println("Enemy Tank does " + damage + " damage");
    }

    @Override
    public void driveForward() {
        System.out.println("Enemy Tank drives forward");
    }

    @Override
    public void assignDriver(String driver) {
        System.out.println("Enemy Tank driver is " + driver);
    }
}
