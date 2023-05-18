package FirstStap.patterns.creation_patterns.factory;

import static FirstStap.patterns.creation_patterns.factory.EnemyShipName.ROCKET;

public class RocketEnemyShip extends EnemyShip {

    public RocketEnemyShip() {
        setName(ROCKET);
        setAmtDamage(100);
    }
}
