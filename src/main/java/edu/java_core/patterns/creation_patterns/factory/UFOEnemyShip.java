package edu.java_core.patterns.creation_patterns.factory;

import static edu.java_core.patterns.creation_patterns.factory.EnemyShipName.UFO;

public class UFOEnemyShip extends EnemyShip {

    public UFOEnemyShip() {
        setName(UFO);
        setAmtDamage(20);
    }
}
