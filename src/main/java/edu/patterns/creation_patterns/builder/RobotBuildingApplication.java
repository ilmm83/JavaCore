package edu.patterns.creation_patterns.builder;

public class RobotBuildingApplication {
    public static void main(String[] args) {
        var engineer = new RobotEngineer(new DefaultRobotBuilder(new Robot()));

        var defaultRobot = engineer.makeDefaultRobot().build();

        System.out.printf("Default tin robot: %s", defaultRobot);


        var customRobot = engineer.builder()
                .setRobotHead("Tin Head")
                .setRobotArms("Tin Arms")
                .setRobotTorso("Tin Torso")
                .setRobotLegs("Tin Legs")
                .build();

        System.out.printf("\nCustom robot: %s", customRobot);
    }
}
