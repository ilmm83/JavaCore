package edu.patterns.creation_patterns.builder;

public class RobotBuildingApplication {
    public static void main(String[] args) {
        var engineer = new RobotEngineer(new DefaultRobotBuilder(new Robot()));

        var defaultRobot = engineer.makeDefaultRobot().build();

        System.out.printf("Default tin robot: %s", defaultRobot);


        var customRobot = engineer.builder()
                .setRobotHead("Golden Head")
                .setRobotArms("Iron Arms")
                .setRobotTorso("Titan Torso")
                .setRobotLegs("Adamant Legs")
                .build();

        System.out.printf("\nCustom robot: %s", customRobot);
    }
}
