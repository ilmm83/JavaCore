package edu.java_core.solid;

//SRP
public class Rectangle implements Shape {
    private final int bigLength;
    private final int smallLength;

    public Rectangle(int smallLength, int bigLength) {
        this.smallLength = smallLength;
        this.bigLength = bigLength;
    }

    @Override
    public double area() {
        return Math.pow((smallLength + bigLength), 2);
    }
}
