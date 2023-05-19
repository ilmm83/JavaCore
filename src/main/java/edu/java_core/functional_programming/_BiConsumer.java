package edu.java_core.functional_programming;

import java.util.function.BiConsumer;

public class _BiConsumer {
    public static void main(String[] args) {
        System.out.println("Usual realisation");
        greetCustomer(
                new Customer("Maria",
                "99999999"), true);

        System.out.println("\nBiConsumer realisation");
        greetCustomerBiConsumerV2.accept(new Customer("John", "1111111"), true);
    }

    //Identical
    static BiConsumer<Customer, Boolean> greetCustomerBiConsumerV2 =
            (customer, showPhoneNumber) -> System.out.println(
                    "Hello " + customer.customerName
                    + ", thanks for registration Phone number "
                    + (showPhoneNumber ? customer.customerPhoneNumber : "******"));


    static void greetCustomer(Customer customer, boolean showPhoneNumber) {
        System.out.println(
                "Hello " + customer.customerName
                + ", thanks for registration Phone number "
                + (showPhoneNumber ? customer.customerPhoneNumber : "******"));
    }

    static class Customer {
        private final String customerName;
        private final String customerPhoneNumber;

        public Customer(String customerName, String customerPhoneNumber) {
            this.customerName = customerName;
            this.customerPhoneNumber = customerPhoneNumber;
        }
    }
}
