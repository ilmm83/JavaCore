package FirstStap.functional_programming;

import java.util.function.Predicate;

public class _Predicate {
    public static void main(String[] args) {
        System.out.println("Usual realisation");
        System.out.println(isPhoneNumberValidator("+79967921822"));

        System.out.println("\nPredicate realisation");
        System.out.println(isPhoneNumberValidatorPredicate.test("+79967921822"));

        System.out.println("\nConcat Predicate realisation");
        System.out.println("Is number valid and contains 1 = " + isPhoneNumberValidatorPredicate
                .and(isNumber1)
                .test("79967921822"));

        System.out.println("\nConcat Predicate realisation");
        System.out.println("Is number valid or contains 1 = " + isPhoneNumberValidatorPredicate
                .or(isNumber1)
                .test("79967921822"));

    }

    static Predicate<String> isNumber1 =
                number1 -> number1.contains("1");

    //Identical
    static Predicate<String> isPhoneNumberValidatorPredicate =
                phoneNumber -> phoneNumber.startsWith("+7")
                        && phoneNumber.length() == 12;

    static boolean isPhoneNumberValidator(String phoneNumber){
        return phoneNumber.startsWith("+7") && phoneNumber.length() == 12;
    }
}
