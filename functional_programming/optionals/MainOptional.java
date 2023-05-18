package FirstStap.functional_programming.optionals;

import java.util.Optional;

public class MainOptional {
    public static void main(String[] args) {
        Person person = new Person("John", "DADA@gmail.com");
        Person person2 = new Person("Malik", null);

        String email = person.getEmail()
                .map(String::toLowerCase)
                .orElseThrow(() -> new IllegalStateException("email not provided"));
        System.out.println(email);


/*      The same code with using if statement
        if (person2.getEmail().isPresent()){
            String email = person2.getEmail().get();
            System.out.println(email);
        }
*/
    }

}

class Person {
    private final String name;
    private final String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}
