package FirstStap.functional_programming;

import java.util.function.BiFunction;

public class _BiFunction {
    public static void main(String[] args) {

        System.out.println("incrementBy1AndMultiply(4, 10) = " + incrementBy1AndMultiply(4, 10));
        System.out.println("\nincrementBy1AndMultiplyBiFunction = " + incrementBy1AndMultiplyBiFunction.apply(0, 5));
    }

    static int incrementBy1AndMultiply(int num, int numToMultiply){
        return (++num) * numToMultiply;
    }

    //Identical
    static BiFunction<Integer, Integer, Integer> incrementBy1AndMultiplyBiFunction =
            (numberToIncrementByOne, numberToMultiplyBy) -> ++numberToIncrementByOne * numberToMultiplyBy;
}
