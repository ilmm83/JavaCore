package edu.java_core.solid;

//SRP
public record Cube(int length) implements Shape {

    @Override
    public double area() {
        return Math.pow(length(), 3);
    }
}
