package edu.java_core.patterns.behavior_patterns.command;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TurnItAllOff implements Command {

    private List<ElectronicDevice> devices;


    @Override
    public void execute() {
        devices.forEach(ElectronicDevice::off);
    }
}
