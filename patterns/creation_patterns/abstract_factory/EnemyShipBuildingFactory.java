package FirstStap.patterns.creation_patterns.abstract_factory;


public abstract class EnemyShipBuildingFactory {

    protected abstract EnemyShip makeEnemyShip(EnemyShipType enemyShipType);

    public EnemyShip orderTheShip(EnemyShipType enemyShipType) {
        var ship = makeEnemyShip(enemyShipType);
        ship.makeShip();

        return ship;
    }
}
