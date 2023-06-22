package edu.patterns.creation_patterns.factory;

public abstract class EnemyShip {

    private EnemyShipName name;
    private double amtDamage;

    void followHerroShip() {
        System.out.printf("%s is following herro ship.\n", name);
    }

    void displayEnemyShip() {
        System.out.printf("%s is on the screen.\n", name);
    }
    void enemyShipShoots() {
        System.out.printf("%s attacks and does %s.\n", name, amtDamage);
    }


    public EnemyShipName getName() {
        return name;
    }

    public void setName(EnemyShipName name) {
        this.name = name;
    }

    public double getAmtDamage() {
        return amtDamage;
    }

    public void setAmtDamage(double amtDamage) {
        this.amtDamage = amtDamage;
    }
}
