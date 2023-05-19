package edu.java_core.patterns.creation_patterns.abstract_factory;

public interface IEnemyShipFactory {

    IESWeapon addWeapon();
    IESEngine addEngine();
}
