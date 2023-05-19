package edu.java_core.functional_programming.optionals;

import java.util.Optional;
import java.util.function.Supplier;

public class _Optional {
    public static void main(String[] args) {
        Optional<String> empty = Optional.empty();
        System.out.println("empty? " + empty.isEmpty());
        System.out.println("\nis present " + empty.isPresent());

        Optional<String> things = Optional.of("One thing");
        System.out.println("\nthings = " + things.orElseGet(() -> "empty thing"));


        Object elseGet = Optional.ofNullable(null)
                .orElseGet(() -> "\ndefault value");
        System.out.println(elseGet);


        Supplier<IllegalStateException> exception = () -> new IllegalStateException("exception");
        String s = Optional.ofNullable("\nNot null")
                .orElseThrow(exception);
        System.out.println(s);


        Optional.ofNullable("\nis present")
                .ifPresent(System.out::println);


        Optional.ofNullable(null)
                .ifPresentOrElse(x -> System.out.println("\n" + x),
                        () -> System.out.println("\nisn't present"));

        Optional<String> stringOptional = Optional.ofNullable("\nLove");
        String orElse = stringOptional
                .map(String::toUpperCase)
                .orElseThrow(() -> new IllegalStateException("Why are you stupid"));
        System.out.println(orElse);
    }

}
