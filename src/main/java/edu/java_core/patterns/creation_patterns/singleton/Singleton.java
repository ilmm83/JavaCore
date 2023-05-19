package edu.java_core.patterns.creation_patterns.singleton;

public class Singleton {

    private static Singleton instance = null;

    private Singleton() { }


    public static Singleton getInstance() {
        return instance == null ? (instance = new Singleton()) : instance;
    }
}
