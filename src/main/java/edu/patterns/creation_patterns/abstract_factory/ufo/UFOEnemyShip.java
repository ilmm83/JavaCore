package edu.patterns.creation_patterns.abstract_factory.ufo;


import edu.patterns.creation_patterns.abstract_factory.EnemyShip;
import edu.patterns.creation_patterns.abstract_factory.IEnemyShipFactory;

import static edu.patterns.creation_patterns.abstract_factory.EnemyShipType.UFO;

public class UFOEnemyShip extends EnemyShip {

    private IEnemyShipFactory factory;

    public UFOEnemyShip(IEnemyShipFactory factory) {
        this.factory = factory;
        setName(UFO.toString());
    }


    @Override
    public void makeShip() {
        System.out.println("Making enemy ship " + getName());

        setWeapon(factory.addWeapon());
        setEngine(factory.addEngine());
    }
}
