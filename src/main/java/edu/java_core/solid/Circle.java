package edu.java_core.solid;

//SRP, ISP
public class Circle implements Shape, TreeDimensionalShape {

    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    private int getRadius() {
        return radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(getRadius(), 2);
    }

    @Override
    public double volume() {
        return 0;
    }
}
