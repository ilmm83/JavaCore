package edu.patterns.structure_patterns.bridge;

public class TVRemotePause extends RemoteButton {

    public TVRemotePause(EntertainmentDevice device) {
        super(device);
    }

    @Override
    public void buttonFivePressed() {
        System.out.println("TV was Paused");
    }
}
