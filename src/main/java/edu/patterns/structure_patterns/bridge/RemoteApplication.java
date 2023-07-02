package edu.patterns.structure_patterns.bridge;

public class RemoteApplication {
    public static void main(String[] args) {
        EntertainmentDevice tv = new TVDevice(0, 5);
        RemoteButton muteTV = new TVRemoteMute(tv);
        RemoteButton pauseTV = new TVRemotePause(tv);

        muteTV.buttonFivePressed();
        muteTV.buttonThreePressed();

        pauseTV.buttonFivePressed();

        System.out.println();

        EntertainmentDevice dvd = new DVDDevice(0, 5);
        RemoteButton muteDVD = new TVRemoteMute(dvd);
        RemoteButton pauseDVD = new TVRemotePause(dvd);

        pauseDVD.buttonThreePressed();
        muteDVD.buttonFourPressed();
        muteDVD.buttonFivePressed();

        dvd.getDeviceFeedback();
    }
}
