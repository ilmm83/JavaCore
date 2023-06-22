package edu.patterns.creation_patterns.abstract_factory.rocket;

import edu.patterns.creation_patterns.abstract_factory.IESEngine;

public class DefaultRocketESEngine implements IESEngine {

    @Override
    public String toString() {
        return "2000 mph";
    }
}
