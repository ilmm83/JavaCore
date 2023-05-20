package edu.java_core.patterns.behavior_patterns.command;

public class Radio implements ElectronicDevice {

    private int volume;


    @Override
    public void on() {
        System.out.println("Radio is on");
    }

    @Override
    public void off() {
        System.out.println("Radio is off");
    }

    @Override
    public void volumeUp() {
        System.out.println("Radio volume is " + ++volume);
    }

    @Override
    public void volumeDown() {
        System.out.println("Radio volume is " + --volume);
    }
}
