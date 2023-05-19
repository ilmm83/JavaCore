package edu.java_core.patterns.creation_patterns.singleton.multithreading_singleton;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MultithreadingSingleton {

    private static MultithreadingSingleton instance = null;

    private String[] scrabbleLetters =
            {
                    "a", "a", "a", "a", "a", "a", "a", "a", "a",

                    "b", "b", "c", "c", "d", "d", "d", "d", "e", "e", "e", "e", "e",

                    "e", "e", "e", "e", "e", "e", "e", "f", "f", "g", "g", "g", "h",

                    "h", "i", "i", "i", "i", "i", "i", "i", "i", "i", "j", "k", "l",

                    "l", "l", "l", "m", "m", "n", "n", "n", "n", "n", "n", "o", "o",

                    "o", "o", "o", "o", "o", "o", "p", "p", "q", "r", "r", "r", "r",

                    "r", "r", "s", "s", "s", "s", "t", "t", "t", "t", "t", "t", "u",

                    "u", "u", "u", "v", "v", "w", "w", "x", "y", "y", "z"
            };

    private final List<String> letters = new LinkedList<>(Arrays.asList(scrabbleLetters));

    // Used to slow down first thread
    private static boolean isPresent = true;

    // Created to keep users from instantiation
    // Only MultithreadingSingleton will be able to instantiate this class
    private MultithreadingSingleton() { }

    // We could make getInstance a synchronized method to force
    // every thread to wait its turn. That way only one thread
    // can access a method at a time. This can really slow everything down though
    public static MultithreadingSingleton getInstance() {
        if (instance == null) {

            // This is here to test what happens if threads try
            // to create instances of this class
            if (isPresent) {
                isPresent = false;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Here we just use synchronized when the first object is created
            synchronized (MultithreadingSingleton.class) {
                if (instance == null) {

                    // If the instance isn't needed it isn't created
                    // This is known as lazy instantiation
                    instance = new MultithreadingSingleton();

                    // Shuffle the letters in the list
                    Collections.shuffle(instance.letters);
                }
            }
        }

        // Under either circumstance this returns the instance
        return instance;
    }

    public List<String> getLetters() {
        return instance.letters;
    }


    public List<String> getTiles(int tillsAmount) {
        // Tiles to be returned to the user
        var tilesToSend = new LinkedList<String>();

        // Cycle through the LinkedList while adding the starting
        // Strings to the to be returned LinkedList while deleting
        // them from letterList

        for (int i = 0; i <= tillsAmount; i++) {
            tilesToSend.add(instance.letters.remove(0));
        }

        // Return the number of letter tiles requested
        return tilesToSend;
    }
}
