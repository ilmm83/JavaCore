package edu.java_core.functional_programming.filesection;

import java.util.function.BiPredicate;
import java.util.function.Function;

public class Lambdas {
    public static void main(String[] args) {
        Function<Integer, String> valueOf = String::valueOf;
        System.out.println(valueOf.apply(12) + "3");


        System.out.println(new Service().compareTwoStrings.test("da", "net"));

    }

}
class Service {
    public BiPredicate<String,String> compareTwoStrings = (s1, s2) -> s2.equals(s1);
}