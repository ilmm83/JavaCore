package edu.patterns.creation_patterns.builder;

public class Robot implements IRobot {

    private String head;
    private String arms;
    private String legs;
    private String torso;


    @Override
    public void setRobotHead(String head) {
        this.head = head;
    }

    @Override
    public void setRobotArms(String arms) {
        this.arms = arms;
    }

    @Override
    public void setRobotLegs(String legs) {
        this.legs = legs;
    }

    @Override
    public void setRobotTorso(String torso) {
        this.torso = torso;
    }

    @Override
    public String toString() {
        return "Robot: \n\t"
                + head + "\n\t"
                + torso + "\n\t"
                + arms + "\n\t"
                + legs + "\n\t";
    }
}
