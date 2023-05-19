package edu.java_core.patterns.creation_patterns.builder;

public class RobotEngineer {

    private IRobotBuilder builder;

    public RobotEngineer(IRobotBuilder builder) {
        this.builder = builder;
    }


    public IRobot build() {
        return this.builder.getRobot();
    }

    public CustomRobotBuilder builder() {
        return new CustomRobotBuilder(new Robot());
    }

    public RobotEngineer makeDefaultRobot() {
        this.builder.buildRobotHead();
        this.builder.buildRobotTorso();
        this.builder.buildRobotArms();
        this.builder.buildRobotLegs();

        return this;
    }
}
