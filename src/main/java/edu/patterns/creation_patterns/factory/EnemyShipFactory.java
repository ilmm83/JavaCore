package edu.patterns.creation_patterns.factory;

import static edu.patterns.creation_patterns.factory.EnemyShipName.ROCKET;
import static edu.patterns.creation_patterns.factory.EnemyShipName.UFO;

public class EnemyShipFactory {

    public static EnemyShip getEnemyShipInstance(EnemyShipName enemyShipName) {
        if (enemyShipName == ROCKET) return new RocketEnemyShip();
        if (enemyShipName == UFO) return new UFOEnemyShip();

        throw new EnemyShipNotFound(String.format("%s not found.", enemyShipName));
    }
}
