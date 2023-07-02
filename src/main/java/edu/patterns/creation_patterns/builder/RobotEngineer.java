package edu.patterns.creation_patterns.builder;

public class RobotEngineer {

    private IRobotBuilder builder;


    public RobotEngineer(IRobotBuilder builder) {
        this.builder = builder;
    }

    public IRobot build() {
        return builder.getRobot();
    }

    public CustomRobotBuilder builder() {
        return new CustomRobotBuilder(new Robot());
    }

    public RobotEngineer makeDefaultRobot() {
        builder.buildRobotHead();
        builder.buildRobotTorso();
        builder.buildRobotArms();
        builder.buildRobotLegs();

        return this;
    }
}
