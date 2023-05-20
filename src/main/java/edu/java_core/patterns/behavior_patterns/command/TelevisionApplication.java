package edu.java_core.patterns.behavior_patterns.command;

import java.util.List;

public class TelevisionApplication {
    public static void main(String[] args) {

        // Take an electronic tv
        ElectronicDevice tv = TVRemote.getDevice();
        ElectronicDevice radio = RadioRemote.getDevice();

        // Set the commands to it
        TurnTVOn onRadioCommand = new TurnTVOn(radio);
        VolumeUp volumeRadioUpCommand = new VolumeUp(radio);
        VolumeDown volumeRadioDownCommand = new VolumeDown(radio);

        TurnTVOn onTVCommand = new TurnTVOn(tv);
        VolumeUp volumeTVUpCommand = new VolumeUp(tv);

        TurnItAllOff shutdown = new TurnItAllOff(List.of(tv, radio));

        // Set commands to the buttons
        DeviceButton onRadioButton = new DeviceButton(onRadioCommand);
        DeviceButton volumeRadioUpButton = new DeviceButton(volumeRadioUpCommand);
        DeviceButton volumeRadioDownButton = new DeviceButton(volumeRadioDownCommand);

        DeviceButton onTVButton = new DeviceButton(onTVCommand);
        DeviceButton volumeTVUpButton = new DeviceButton(volumeTVUpCommand);

        DeviceButton turnAllDevisesOff = new DeviceButton(shutdown);

        // Press the buttons
        onTVButton.press();
        volumeTVUpButton.press();

        onRadioButton.press();
        volumeRadioUpButton.press();
        volumeRadioUpButton.press();
        volumeRadioDownButton.press();

        turnAllDevisesOff.press();
    }
}
