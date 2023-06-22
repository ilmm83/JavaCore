package edu.patterns.creation_patterns.abstract_factory.ufo;

import edu.patterns.creation_patterns.abstract_factory.IESEngine;
import edu.patterns.creation_patterns.abstract_factory.IESWeapon;
import edu.patterns.creation_patterns.abstract_factory.IEnemyShipFactory;

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
