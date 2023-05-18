package FirstStap.patterns.creation_patterns.builder;

public class CustomRobotBuilder {

    private IRobot robot;

    public CustomRobotBuilder(IRobot robot) {
        this.robot = robot;
    }


    public CustomRobotBuilder setRobotHead(String head) {
        robot.setRobotHead(head);
        return this;
    }

    public CustomRobotBuilder setRobotTorso(String torso) {
        robot.setRobotTorso(torso);
        return this;
    }

    public CustomRobotBuilder setRobotArms(String arms) {
        robot.setRobotArms(arms);
        return this;
    }

    public CustomRobotBuilder setRobotLegs(String legs) {
        robot.setRobotLegs(legs);
        return this;
    }

    public IRobot build() {
        return robot;
    }
}
