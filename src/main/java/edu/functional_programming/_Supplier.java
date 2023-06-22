package edu.functional_programming;

import java.util.List;
import java.util.function.Supplier;

public class _Supplier {
    public static void main(String[] args) {
        System.out.println("getDBConnectingSupplier = " + getDBConnectingSupplier.get());
        getListOfIntegersSupplier.get().forEach(System.out::println);
    }

    static String getDBConnecting(){
        return "jdbc:postgres//localhost:5432/users";
    }

    //Identical
    static Supplier<String> getDBConnectingSupplier =
            () -> "jdbc:postgres//localhost:5432/users";

    static Supplier<List<Integer>> getListOfIntegersSupplier =
            () -> List.of(
                    1,
                    2,
                    3
            );
}
