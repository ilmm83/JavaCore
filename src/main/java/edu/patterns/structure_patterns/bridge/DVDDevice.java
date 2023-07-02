package edu.patterns.structure_patterns.bridge;

public class DVDDevice extends EntertainmentDevice {

    public DVDDevice(int deviceState, int maxSetting) {
        this.deviceState = deviceState;
        this.maxSetting = maxSetting;
    }

    @Override
    protected void pressButtonOne() {
        System.out.println("Next track");
        deviceState++;
    }

    @Override
    protected void pressButtonTwo() {
        System.out.println("Previous track");
        deviceState--;
    }
}
