package edu.patterns.creation_patterns.factory;

import static edu.patterns.creation_patterns.factory.EnemyShipName.UFO;

public class UFOEnemyShip extends EnemyShip {

    public UFOEnemyShip() {
        setName(UFO);
        setAmtDamage(20);
    }
}
