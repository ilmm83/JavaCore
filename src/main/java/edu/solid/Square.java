package edu.solid;

public class Square implements Shape {
    private final int length;

    public Square(int length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    @Override
    public double area() {
        return Math.pow(getLength(), 2);
    }
}
