package edu.patterns.creation_patterns.singleton.multithreading_singleton;

public class ScrabbleApplication {
    public static void main(String[] args) {
        new Thread(new GetTheTiles()).start();
        new Thread(new GetTheTiles()).start();
    }
}
