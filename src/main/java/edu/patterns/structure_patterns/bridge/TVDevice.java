package edu.patterns.structure_patterns.bridge;

public class TVDevice extends EntertainmentDevice {

    public TVDevice(int deviceState, int maxSetting) {
        this.deviceState = deviceState;
        this.maxSetting = maxSetting;
    }

    @Override
    protected void pressButtonOne() {
        System.out.println("Channel Up");
        deviceState++;
    }

    @Override
    protected void pressButtonTwo() {
        System.out.println("Channel Down");
        deviceState--;
    }
}
