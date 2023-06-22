package edu.patterns.creation_patterns.prototype;

public class Sheep implements Animal {

    @Override
    public Animal makeCopy() {
        Sheep sheep;

        try {
            sheep = (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        return sheep;
    }
}
