package edu.java_core.datastructures.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WorkingWithMap {
    public static void main(String[] args) {

        Map<Integer, Person> personMap = new HashMap<>();
        personMap.put(1, new Person("John", 12));
        personMap.put(2, new Person("Alex", 15));
        personMap.put(3, new Person("Maria", 7));

        System.out.println(personMap);
        System.out.println("\nmap size " + personMap.size());
        System.out.println("\n" + personMap.containsKey(4));
        System.out.println("\nkey set " + personMap.keySet());
        System.out.println("\nentry set " + personMap.entrySet());

        System.out.println();
        personMap.entrySet().forEach(System.out::println);

        Map<Human, Integer> humanMap = new HashMap<>();
        humanMap.put(new Human("Boba"), 1);

        humanMap.forEach((key, number) -> System.out.println("\n" + key + " " + number));

    }

    // if you use class as a key type parameter, you should override equals and hashCode
    static class Human {
        private final String name;

        public Human(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Human human = (Human) o;
            return Objects.equals(name, human.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Human{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    record Person(String name, int age) {}
}
