package FirstStap.patterns.creation_patterns.abstract_factory.ufo;


import FirstStap.patterns.creation_patterns.abstract_factory.IEnemyShipFactory;

import static FirstStap.patterns.creation_patterns.abstract_factory.EnemyShipType.BOSS_UFO;

public class BossUFOEnemyShip extends UFOEnemyShip {

    private IEnemyShipFactory factory;

    public BossUFOEnemyShip(IEnemyShipFactory factory) {
        super(factory);

        this.factory = factory;
        setName(BOSS_UFO.toString());
    }

    
    @Override
    public void makeShip() {
        System.out.println("Making enemy ship " + getName());

        setEngine(factory.addEngine());
        setWeapon(factory.addWeapon());
    }
}
