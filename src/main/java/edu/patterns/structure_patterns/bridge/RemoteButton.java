package edu.patterns.structure_patterns.bridge;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class RemoteButton {

    private EntertainmentDevice device;


    public void buttonThreePressed() {
        device.pressButtonThree();
    }

    public void buttonFourPressed() {
        device.pressButtonFour();
    }

    public void deviceFeedback() {
        device.getDeviceFeedback();
    }

    public abstract void buttonFivePressed();
}
