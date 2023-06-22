package edu.patterns.behavior_patterns.command;

public class RadioRemote {

    public static ElectronicDevice getDevice() {
        return new Radio();
    }
}
