package edu.java_core.patterns.behavior_patterns.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VolumeDown implements Command {

    private ElectronicDevice device;


    @Override
    public void execute() {
        device.volumeDown();
    }
}
