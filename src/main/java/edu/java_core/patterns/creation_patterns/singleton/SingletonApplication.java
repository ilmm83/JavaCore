package edu.java_core.patterns.creation_patterns.singleton;

public class SingletonApplication {
    public static void main(String[] args) {
        var id1 = System.identityHashCode(Singleton.getInstance());
        var id2 = System.identityHashCode(Singleton.getInstance());

        System.out.printf("\nThe first instance %d and the second instance %d is the same object\n", id1, id2);
    }
}
