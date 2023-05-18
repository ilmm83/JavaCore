package FirstStap.solid;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IAreaCalculator iAreaCalculator = new AreaCalculator();
        ShapesPrinter printer = new ShapesPrinter(iAreaCalculator);
        Circle circle = new Circle(2);
        Square square = new Square(2);
        Rectangle rectangle = new Rectangle(2, 5);
        Cube cube = new Cube(2);

        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(circle);
        shapeList.add(square);
        shapeList.add(cube);
        shapeList.add(rectangle);

        System.out.println(printer.json(shapeList));
    }
}
