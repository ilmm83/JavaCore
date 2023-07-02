package edu.patterns.creation_patterns.abstract_factory.rocket;


import edu.patterns.creation_patterns.abstract_factory.EnemyShip;
import edu.patterns.creation_patterns.abstract_factory.IEnemyShipFactory;

import static edu.patterns.creation_patterns.abstract_factory.EnemyShipType.ROCKET;

public class RocketEnemyShip extends EnemyShip {

    private IEnemyShipFactory factory;


    public RocketEnemyShip(IEnemyShipFactory factory) {
        this.factory = factory;

        setName(ROCKET.name());
    }

    @Override
    public void makeShip() {
        System.out.println("Making enemy ship " + getName());

        setEngine(factory.addEngine());
        setWeapon(factory.addWeapon());
    }
}
