package edu.solid;

import java.util.List;

//SRP, DIP
public class ShapesPrinter {

    private final IAreaCalculator iAreaCalculator;

    public ShapesPrinter(IAreaCalculator iAreaCalculator) {
        this.iAreaCalculator = iAreaCalculator;
    }

    public String json(List<Shape> shapes) {
        return "shapes sum = %s".formatted(iAreaCalculator.sum(shapes));

    }

    public String csv(List<Shape> shapes) {
        return "shapes_sum, %s".formatted(iAreaCalculator.sum(shapes));
    }
}
