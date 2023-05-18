package FirstStap.patterns.creation_patterns.singleton.multithreading_singleton;

public class GetTheTiles implements Runnable {

    @Override
    public void run() {
        var singleton = MultithreadingSingleton.getInstance();

        System.out.printf("\nInstance ID: %d", System.identityHashCode(singleton));
        System.out.printf("\n%s", singleton.getLetters());
        System.out.printf("\nPlayer : %s\n", singleton.getTiles(7));
    }
}
