package FirstStap.patterns.creation_patterns.abstract_factory.rocket;

import FirstStap.patterns.creation_patterns.abstract_factory.IESWeapon;

public class DefaultRocketESWeapon implements IESWeapon {

    @Override
    public String toString() {
        return "30 damage";
    }
}
