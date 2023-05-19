package edu.java_core.patterns.creation_patterns.abstract_factory;

public abstract class EnemyShip {

    private String name;
    private IESWeapon weapon;
    private IESEngine engine;


    public void followHerroShip() {
        System.out.printf("%s is following hero ship.\n", name);
    }

    public void displayEnemyShip() {
        System.out.printf("%s is on the screen.\n", name);
    }

    public void enemyShipShoots() {
        System.out.printf("%s attacks and does %s.\n", name, weapon);
    }

    protected abstract void makeShip();


    protected void setName(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    protected void setWeapon(IESWeapon weapon) {
        this.weapon = weapon;
    }

    protected void setEngine(IESEngine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return String.format("The %s has a top speed of %s and an attack power of %s", name, engine, weapon);
    }
}
