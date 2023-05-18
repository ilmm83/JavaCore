package FirstStap.datastructures.collections;

import java.util.LinkedList;
import java.util.Queue;

public class WorkingWithQueue {
    public static void main(String[] args) {

        // FIFO
        Queue<Person> queue = new LinkedList<>();
        queue.add(new Person("John", 22));
        queue.add(new Person("Maria", 33));
        queue.add(new Person("Alex", 11));


        System.out.println("Size before " + queue.size());
        System.out.println("Peek " + queue.peek());
        System.out.println("Size after " + queue.size());


        System.out.println("\nSize " + queue.size());
        System.out.println("Poll " + queue.poll());
        System.out.println("Size after " + queue.size());


    }
    static record Person(String name, int age){}
}
