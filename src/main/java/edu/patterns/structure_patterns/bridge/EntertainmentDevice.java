package edu.patterns.structure_patterns.bridge;

public abstract class EntertainmentDevice {

    protected int deviceState;
    protected int maxSetting;
    protected int volumeLevel = 0;


    protected abstract void pressButtonOne();
    protected abstract void pressButtonTwo();

    public void getDeviceFeedback() {
        if (deviceState > maxSetting || deviceState < 0) deviceState = 0;

        System.out.println("On " + deviceState);
    }

    public void pressButtonThree() {
        System.out.println("Volume up to: " + ++volumeLevel);
    }

    public void pressButtonFour() {
        System.out.println("Volume down to: " + --volumeLevel);
    }
}
