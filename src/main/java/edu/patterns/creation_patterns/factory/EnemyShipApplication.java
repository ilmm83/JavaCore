package edu.patterns.creation_patterns.factory;


import static edu.patterns.creation_patterns.factory.EnemyShipName.ROCKET;
import static edu.patterns.creation_patterns.factory.EnemyShipName.UFO;

public class EnemyShipApplication {
    public static void main(String[] args) {
        var ufo = EnemyShipFactory.getEnemyShipInstance(UFO);
        var rocket = EnemyShipFactory.getEnemyShipInstance(ROCKET);

        doStuff(ufo);
        doStuff(rocket);
    }

    private static void doStuff(EnemyShip enemyShip) {
        enemyShip.displayEnemyShip();
        enemyShip.followHerroShip();
        enemyShip.enemyShipShoots();
    }
}
