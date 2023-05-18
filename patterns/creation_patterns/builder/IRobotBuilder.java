package FirstStap.patterns.creation_patterns.builder;

public interface IRobotBuilder {

    void buildRobotHead();
    void buildRobotArms();
    void buildRobotLegs();
    void buildRobotTorso();
    IRobot getRobot();
}
