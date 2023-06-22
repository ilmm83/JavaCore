package edu.patterns.structure_patterns.adapter;

import java.util.Random;

public class EnemyRobot {

    private Random generator = new Random();


    public void smashWithHands() {
        var damage = generator.nextInt(10) + 1;
        System.out.println("Enemy Robot does " + damage + " damage");
    }

    public void walkForward() {
        System.out.println("Enemy Robot walks forward");
    }

    public void reactToHuman(String driver) {
        System.out.println("Enemy Robot tramps on " + driver);
    }
}
