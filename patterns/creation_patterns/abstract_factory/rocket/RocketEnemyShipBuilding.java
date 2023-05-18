package FirstStap.patterns.creation_patterns.abstract_factory.rocket;


import FirstStap.patterns.creation_patterns.abstract_factory.EnemyShip;
import FirstStap.patterns.creation_patterns.abstract_factory.EnemyShipBuildingFactory;
import FirstStap.patterns.creation_patterns.abstract_factory.EnemyShipType;

import static FirstStap.patterns.creation_patterns.abstract_factory.EnemyShipType.ROCKET;

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
