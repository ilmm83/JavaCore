package edu.java_core.patterns.creation_patterns.abstract_factory.ufo;

import edu.java_core.patterns.creation_patterns.abstract_factory.EnemyShip;
import edu.java_core.patterns.creation_patterns.abstract_factory.EnemyShipBuildingFactory;
import edu.java_core.patterns.creation_patterns.abstract_factory.EnemyShipType;

import static edu.java_core.patterns.creation_patterns.abstract_factory.EnemyShipType.BOSS_UFO;
import static edu.java_core.patterns.creation_patterns.abstract_factory.EnemyShipType.UFO;

public class UFOEnemyShipBuilding extends EnemyShipBuildingFactory {

    @Override
    protected EnemyShip makeEnemyShip(EnemyShipType shipType) {
        EnemyShip ship = null;

        if (shipType == UFO) {
            var factory = new UFOEnemyShipFactory();
            ship = new UFOEnemyShip(factory);
        }
        if (shipType == BOSS_UFO) {
            var factory = new BossUFOEnemyShipFactory();
            ship = new BossUFOEnemyShip(factory);
        }

        return ship;
    }
}
