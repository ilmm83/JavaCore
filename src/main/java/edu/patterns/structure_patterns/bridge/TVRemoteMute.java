package edu.patterns.structure_patterns.bridge;

public class TVRemoteMute extends RemoteButton {

    public TVRemoteMute(EntertainmentDevice device) {
        super(device);
    }

    @Override
    public void buttonFivePressed() {
        System.out.println("TV was Muted");
    }
}
