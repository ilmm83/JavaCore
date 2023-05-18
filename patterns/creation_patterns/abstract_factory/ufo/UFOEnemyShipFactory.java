package FirstStap.patterns.creation_patterns.abstract_factory.ufo;

import FirstStap.patterns.creation_patterns.abstract_factory.IESEngine;
import FirstStap.patterns.creation_patterns.abstract_factory.IESWeapon;
import FirstStap.patterns.creation_patterns.abstract_factory.IEnemyShipFactory;

public class UFOEnemyShipFactory implements IEnemyShipFactory {

    @Override
    public IESWeapon addWeapon() {
        return new DefaultUFOESWeapon();
    }

    @Override
    public IESEngine addEngine() {
        return new DefaultUFOESEngine();
    }
}
