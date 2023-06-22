package edu.patterns.creation_patterns.builder;

public class DefaultRobotBuilder implements IRobotBuilder {

    private IRobot robot;

    public DefaultRobotBuilder(IRobot robot) {
        this.robot = robot;
    }


    @Override
    public void buildRobotHead() {
        robot.setRobotHead("Iron Head");
    }

    @Override
    public void buildRobotArms() {
        robot.setRobotArms("Iron Arms");
    }

    @Override
    public void buildRobotLegs() {
        robot.setRobotLegs("Iron Legs");
    }

    @Override
    public void buildRobotTorso() {
        robot.setRobotTorso("Iron Torso");
    }

    @Override
    public IRobot getRobot() {
        return this.robot;
    }
}
