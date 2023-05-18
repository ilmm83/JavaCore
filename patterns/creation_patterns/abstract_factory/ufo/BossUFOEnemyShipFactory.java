package FirstStap.patterns.creation_patterns.abstract_factory.ufo;

import FirstStap.patterns.creation_patterns.abstract_factory.IESEngine;
import FirstStap.patterns.creation_patterns.abstract_factory.IESWeapon;
import FirstStap.patterns.creation_patterns.abstract_factory.IEnemyShipFactory;

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
