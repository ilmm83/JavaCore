package edu.java_core.datastructures.collections;

import java.util.LinkedList;
import java.util.ListIterator;

public class WorkingWithLinkedList {
    public static void main(String[] args) {

        LinkedList<Person> linkedList = new LinkedList<>();
        linkedList.add(new Person("John", 12));
        linkedList.addLast(new Person("Alex", 15));
        linkedList.addFirst(new Person("Maria", 7));

        ListIterator<Person> personListIterator = linkedList.listIterator();
        while (personListIterator.hasNext()) {
            System.out.println(personListIterator.next() + " number " + personListIterator.nextIndex());
        }

        System.out.println();

        while (personListIterator.hasPrevious()) {
            System.out.println(personListIterator.previous() + " number " + personListIterator.nextIndex());
        }

        System.out.println();

        linkedList.stream()
                .filter(x -> x.age > 7)
                .forEach(System.out::println);
    }

    static record Person(String name, int age) {
    }
}
