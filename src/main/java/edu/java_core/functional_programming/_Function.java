package edu.java_core.functional_programming;

import java.util.function.Function;

public class _Function {
    public static void main(String[] args) {
        int increment = incrementByOne(0);
        System.out.println("increment1 = " + increment + "\n");

        int increment2 = incrementByOneFunction.apply(increment);
        System.out.println("increment2 = " + increment2 + "\n");

        int multiply = multiplyBy10Function.apply(increment2);
        System.out.println("multiply = " + multiply + "\n");

        //Concat two examples above
        Function<Integer, Integer> addBy1AndMultiplyBy10 =
                incrementByOneFunction.andThen(multiplyBy10Function);

        System.out.println("addBy1AndMultiplyBy10 = " + addBy1AndMultiplyBy10.apply(4));
    }

    static int incrementByOne(int number){
        return  number + 1;
    }

    // Identical function
    static Function<Integer, Integer> incrementByOneFunction = number -> ++number;

    static Function<Integer, Integer> multiplyBy10Function = number -> number *= 10;

}
