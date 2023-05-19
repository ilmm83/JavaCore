package edu.java_core.patterns.creation_patterns.abstract_factory.rocket;


import edu.java_core.patterns.creation_patterns.abstract_factory.EnemyShip;
import edu.java_core.patterns.creation_patterns.abstract_factory.IEnemyShipFactory;

import static edu.java_core.patterns.creation_patterns.abstract_factory.EnemyShipType.ROCKET;

public class RocketEnemyShip extends EnemyShip {

    private IEnemyShipFactory factory;

    public RocketEnemyShip(IEnemyShipFactory factory) {
        this.factory = factory;

        setName(ROCKET.toString());
    }

    @Override
    public void makeShip() {
        System.out.println("Making enemy ship " + getName());

        setEngine(factory.addEngine());
        setWeapon(factory.addWeapon());
    }
}
