package edu.patterns.creation_patterns.prototype;

import lombok.SneakyThrows;

public class Sheep implements Animal {

    @Override
    @SneakyThrows
    public Animal makeCopy() {
        return (Sheep) super.clone();
    }
}
