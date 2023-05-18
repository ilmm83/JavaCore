package FirstStap.patterns.creation_patterns.factory;

import static FirstStap.patterns.creation_patterns.factory.EnemyShipName.UFO;

public class UFOEnemyShip extends EnemyShip {

    public UFOEnemyShip() {
        setName(UFO);
        setAmtDamage(20);
    }
}
