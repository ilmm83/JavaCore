package edu.patterns.behavior_patterns.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VolumeUp implements Command {

    private ElectronicDevice device;


    @Override
    public void execute() {
        device.volumeUp();
    }
}
