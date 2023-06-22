package edu.patterns.creation_patterns.abstract_factory.rocket;


import edu.patterns.creation_patterns.abstract_factory.EnemyShip;
import edu.patterns.creation_patterns.abstract_factory.EnemyShipBuildingFactory;
import edu.patterns.creation_patterns.abstract_factory.EnemyShipType;

import static edu.patterns.creation_patterns.abstract_factory.EnemyShipType.ROCKET;

public class RocketEnemyShipBuilding extends EnemyShipBuildingFactory {

    @Override
    protected EnemyShip makeEnemyShip(EnemyShipType shipType) {
        EnemyShip rocket = null;

        if (shipType == ROCKET) {
            var factory = new RocketEnemyShipFactory();
            rocket = new RocketEnemyShip(factory);
        }

        return rocket;
    }
}
