package FirstStap.functional_programming.combinatorpattern;

import java.time.LocalDate;

import static FirstStap.functional_programming.combinatorpattern.CustomerRegistrationValidator.*;
import static FirstStap.functional_programming.combinatorpattern.CustomerRegistrationValidator.ValidationResult.SUCCESS;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("John",
                "test@mail.ru",
                "090090909",
                LocalDate.of(2000, 06, 2));

//        CustomerValidatorService customerValidator = new CustomerValidatorService();
//        System.out.println(customerValidator.isValid(customer));

        // if valid we store customer in db

        // Using combinator pattern
        ValidationResult result = isEmailValid()
                .and(isPhoneNumberValid())
                .and(isAdult())
                .apply(customer);

        System.out.println(result);

        if (result != SUCCESS){
            throw new IllegalStateException(result.name());
        }

    }
}
