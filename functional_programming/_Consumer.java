package FirstStap.functional_programming;

import java.util.function.Consumer;

public class _Consumer {
    public static void main(String[] args) {
        System.out.println("Usual realisation");
        greetCustomer((new Customer("Maria", "999999")));

        System.out.println("\nConsumer<T> interface realisation");
        greetCustomerConsumer.accept(new Customer("John", "11111"));
    }

    //Identical
    static Consumer<Customer> greetCustomerConsumer =
            customer -> System.out.println("Hello " + customer.customerName
                    + ", thanks for registration Phone number "
                    + customer.customerPhoneNumber);


    static void greetCustomer(Customer customer){
        System.out.println("Hello " + customer.customerName
                + ", thanks for registration Phone number "
                + customer.customerPhoneNumber);
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
