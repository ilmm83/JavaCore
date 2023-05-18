package FirstStap.patterns.creation_patterns.factory;

import static FirstStap.patterns.creation_patterns.factory.EnemyShipName.*;

public class EnemyShipApplication {
    public static void main(String[] args) {
        var Ufo = EnemyShipFactory.getEnemyShipInstance(UFO);
        var Rocket = EnemyShipFactory.getEnemyShipInstance(ROCKET);

        doStuff(Ufo);
        doStuff(Rocket);
    }

    private static void doStuff(EnemyShip enemyShip) {
        enemyShip.displayEnemyShip();
        enemyShip.followHerroShip();
        enemyShip.enemyShipShoots();
    }
}
