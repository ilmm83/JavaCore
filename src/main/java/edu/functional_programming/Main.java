package edu.functional_programming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class Main {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("John", Gender.MALE),
                new Person("Alice", Gender.FEMALE),
                new Person("Mary", Gender.FEMALE)
        );

        // Imperative Approach
        System.out.println("------Imperative Approach------\n");

        List<Person> females = new ArrayList<>();
        for (Person person : people) {
            if (Gender.FEMALE.equals(person.gender)) {
                females.add(person);
            }
        }

        for (var person : females) {
            System.out.println(person.name + " Gender: " + person.gender);
        }


        System.out.println("\n------Declarative Approach------\n");

        //Declarative Approach
        people.stream()
                .filter(person -> Gender.FEMALE.equals(person.gender))
                .forEach(person -> System.out.println(person.name + " Gender: " + person.gender));

    }

    static class Person {
        private final String name;
        private final Gender gender;


        Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }
    }

    public enum Gender {
        MALE, FEMALE
    }
}
