package edu.java_core.patterns.behavior_patterns.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeviceButton {

    private Command command;


    public void press() {
        command.execute();
    }
}
