package edu.java_core.datastructures.collections;

import java.util.Stack;

public class WorkingWithStack {
    public static void main(String[] args) {

        // LIFO
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Size before " + stack.size());
        System.out.println("Peek " + stack.peek());
        System.out.println("Size after " + stack.size());


        System.out.println("\nSize " + stack.size());
        System.out.println("Pop " + stack.pop());
        System.out.println("Size after " + stack.size());

    }
}
