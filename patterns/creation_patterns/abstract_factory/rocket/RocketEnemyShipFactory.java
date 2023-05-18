package FirstStap.patterns.creation_patterns.abstract_factory.rocket;

import FirstStap.patterns.creation_patterns.abstract_factory.IESEngine;
import FirstStap.patterns.creation_patterns.abstract_factory.IESWeapon;
import FirstStap.patterns.creation_patterns.abstract_factory.IEnemyShipFactory;

public class RocketEnemyShipFactory implements IEnemyShipFactory {

    @Override
    public IESWeapon addWeapon() {
        return new DefaultRocketESWeapon();
    }

    @Override
    public IESEngine addEngine() {
        return new DefaultRocketESEngine();
    }
}
