package edu.java_core.patterns.creation_patterns.abstract_factory.ufo;

import edu.java_core.patterns.creation_patterns.abstract_factory.IESEngine;
import edu.java_core.patterns.creation_patterns.abstract_factory.IESWeapon;
import edu.java_core.patterns.creation_patterns.abstract_factory.IEnemyShipFactory;

public class BossUFOEnemyShipFactory implements IEnemyShipFactory {

    @Override
    public IESWeapon addWeapon() {
        return new DefaultBossUFOESWeapon();
    }

    @Override
    public IESEngine addEngine() {
        return new DefaultBossUFOESEngine();
    }
}
