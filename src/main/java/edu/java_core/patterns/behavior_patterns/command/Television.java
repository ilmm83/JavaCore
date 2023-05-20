package edu.java_core.patterns.behavior_patterns.command;

public class Television implements ElectronicDevice {

    private int volume = 0;


    @Override
    public void on() {
        System.out.println("Television is on");
    }

    @Override
    public void off() {
        System.out.println("Television is off");
    }

    @Override
    public void volumeUp() {
        System.out.println("Volume is " + ++volume);
    }

    @Override
    public void volumeDown() {
        System.out.println("Volume is " + --volume);
    }
}
