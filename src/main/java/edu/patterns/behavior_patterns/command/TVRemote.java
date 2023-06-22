package edu.patterns.behavior_patterns.command;

public class TVRemote {

    public static ElectronicDevice getDevice() {
        return new Television();
    }
}
