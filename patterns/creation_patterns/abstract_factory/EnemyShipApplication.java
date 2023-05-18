package FirstStap.patterns.creation_patterns.abstract_factory;

import FirstStap.patterns.creation_patterns.abstract_factory.rocket.RocketEnemyShipBuilding;
import FirstStap.patterns.creation_patterns.abstract_factory.ufo.UFOEnemyShipBuilding;

public class EnemyShipApplication {
    public static void main(String[] args) {
        var makeUFOs = new UFOEnemyShipBuilding();
        var ufo = makeUFOs.orderTheShip(EnemyShipType.UFO);
        doEnemyShipStuff(ufo);

        var bossUfo = makeUFOs.orderTheShip(EnemyShipType.BOSS_UFO);
        doEnemyShipStuff(bossUfo);

        var makeRockets = new RocketEnemyShipBuilding();
        var rocket = makeRockets.orderTheShip(EnemyShipType.ROCKET);
        doEnemyShipStuff(rocket);
    }

    private static void doEnemyShipStuff(EnemyShip enemyShip) {
        System.out.println();
        enemyShip.displayEnemyShip();
        enemyShip.followHerroShip();
        enemyShip.enemyShipShoots();
    }
}
