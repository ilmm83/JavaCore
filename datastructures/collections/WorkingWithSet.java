package FirstStap.datastructures.collections;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WorkingWithSet {
    public static void main(String[] args) {

        Set<Ball> balls = new HashSet<>();
        balls.add(new Ball("Blue"));
        balls.add(new Ball("White"));
        balls.add(new Ball("Red"));
        // Duplicates are delete
        balls.add(new Ball("Red"));

        System.out.println("Balls size " + balls.size());
        balls.forEach(System.out::println);

        balls.remove(new Ball("Red"));
        System.out.println("Balls size after remove one " + balls.size());
        balls.forEach(System.out::println);

    }
//    record Ball(String color){}
}
// if you use a class as a parameter type,
// then override equals and hashcode (and toString) to avoid duplicates
class Ball{
    private final String color;

    public Ball(String color){
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return Objects.equals(color, ball.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "color='" + color + '\'' +
                '}';
    }
}