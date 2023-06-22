package edu.patterns.creation_patterns.prototype;

public class PrototypeApplication {
    public static void main(String[] args) {
        var sheep = new Sheep();
        System.out.println(System.identityHashCode(sheep));

        var sheepCopy = sheep.makeCopy();
        System.out.println(System.identityHashCode(sheepCopy));

    }
}
