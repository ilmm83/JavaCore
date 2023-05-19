package edu.java_core.patterns.creation_patterns.abstract_factory.rocket;

import edu.java_core.patterns.creation_patterns.abstract_factory.IESEngine;
import edu.java_core.patterns.creation_patterns.abstract_factory.IESWeapon;
import edu.java_core.patterns.creation_patterns.abstract_factory.IEnemyShipFactory;

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
