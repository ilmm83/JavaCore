package FirstStap.functional_programming.streams;

import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static FirstStap.functional_programming.streams._Stream.Gender.FEMALE;
import static FirstStap.functional_programming.streams._Stream.Gender.MALE;


public class _Stream {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("John", MALE),
                new Person("Alice", FEMALE),
                new Person("Mary", FEMALE)
        );

        Predicate<Person> personPredicate = person -> FEMALE.equals(person.gender);
        boolean b = people.stream()
                .noneMatch(personPredicate);
        System.out.println(b);

        System.out.println();

        people.stream()
                .map(person -> person.name)// V Functional T,V
                .mapToInt(String::length)
                .forEach(System.out::println);

        System.out.println();

        people.stream()
                .map(person -> person.gender) // Map each object of List ot Gender
                .collect(Collectors.toSet()) // Collect to Set for remove duplicates
                .forEach(System.out::println); // take out to shell

        System.out.println();

        Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 6 S", 54000), new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000));

        System.out.println();
        // Map
//        phoneStream
//                .map(phone -> "Mark" + phone.getName() + " price " + phone.getPrice())
//                .forEach(System.out::println);


        // FlatMap - one-to-many
        Function<Phone, Stream<? extends String>> phoneStreamFunction = phone -> Stream.of(
                String.format("\nMark: %s price without discount, %d", phone.getName(), phone.getPrice()),
                String.format("Mark: %s price with discount, %d", phone.getName(), phone.getPrice() - (int) (phone.getPrice() * 0.1))
        );
        phoneStream
                .flatMap(phoneStreamFunction)
                .forEach(System.out::println);

        System.out.println();

        //  Work while match to the condition
        Stream<Integer> numbersOfStream = Stream.of(-3, -2, -1, 0, 1, 2, 3);
        numbersOfStream
                .takeWhile(n -> n < 0)
                .forEach(System.out::println);

        System.out.println();

        // The dropWhile() method does the opposite -
        // it skips elements of the stream that match the condition
        // until it encounters an element that does NOT match the condition:
        Stream<Integer> numbersOfStream2 = Stream.of(-3, -2, -1, 0, 1, 2, 3);
        numbersOfStream2
                .dropWhile(n -> n < 0)
                .forEach(System.out::println);

        System.out.println();

        // concat - concatenation two streams
        Stream<String> people1 = Stream.of("Tom", "Bob", "Sam");
        Stream<String> people2 = Stream.of("Alice", "Kate", "Sam");
        Stream.concat(people1, people2)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\n---------------------");

        System.out.println("\n----------Reduce---------");


        //                         REDUCE

        // First implementation
        Stream<Integer> numReduce = Stream.of(1, 2, 3, 4, 5);
        Optional<Integer> reduceIntegers = numReduce
                .reduce((x, y) -> x * y);
        System.out.println(reduceIntegers);

        System.out.println("\n---------------------");

        System.out.println("\n---------------------");

        Stream<String> stringReduce = Stream.of("мама", "мыла", "раму");
        Optional<String> reduceStrings = stringReduce
                .reduce((x, y) -> x + " " + y);
        System.out.println(reduceStrings);

        System.out.println("\n---------------------");

        System.out.println("\n---------------------");

        // Second implementation
        Stream<Integer> numberStream = Stream.of(-4, 3, -2, 1);
        int identity = 1;
        Integer reduce = numberStream
                .reduce(identity, (x, y) -> x * y);
        System.out.println(reduce);

        System.out.println("\n---------------------");

        System.out.println("\n---------------------");

        //Third implementation
        Stream<Phone> phoneStream2 = Stream.of(new Phone("iPhone 6 S", 54000),
                new Phone("Lumia 950", 45000),
                new Phone("Samsung Galaxy S 6", 40000),
                new Phone("LG G 4", 32000));

        int sum2 = phoneStream2.parallel().reduce(0,
                (x, y) -> {
                    if (y.getPrice() < 50000)
                        return x + y.getPrice();
                    else
                        return x;
                },
                Integer::sum // Third parameter works if stream is parallel
        );
        System.out.println(sum2);

        System.out.println("\n---------------------");

        System.out.println("\n---------------------");

        Stream<Integer> numberStream2 = Stream.of(1, 2, 3, 4, 5, 6);
        int result = numberStream2.reduce(0, (sum, y) -> {

            System.out.println("-------");
            System.out.println("y=" + y);
            System.out.println("sum=" + sum);
            System.out.println("-------");

            return y < 5 ? sum + y : sum;
        });

        System.out.println(result);

        System.out.println("\n---------------------");

        System.out.println("\n----------Collect---------");

        //                      COLLECT

        Stream<String> phones = Stream.of("iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        List<String> filterPhones = phones.filter(s -> s.length() < 12)
                .collect(ArrayList::new, // create ArrayList
                        ArrayList::add, // add elements in list
                        ArrayList::addAll // add list in other list
                );
        filterPhones.forEach(System.out::println);

        System.out.println("\n---------------------");

        Stream<Phone> phoneStream3 = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));

        Map<String, List<Phone>> phonesByCompany = phoneStream3.collect(
                Collectors.groupingBy(Phone::getCompany) // group by company
        );

        for (var item : phonesByCompany.entrySet()) { // iterating over the entry set
            System.out.println(item.getKey()); // getting company name
            for (var phone : item.getValue()) { // iterating by Phone names
                System.out.println(phone.getName()); // print phone name
            }
            System.out.println();
        }

        System.out.println("\n---------------------");

        System.out.println("\n----------partitionBy---------");

        Stream<Phone> phoneStream4 = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));


        Map<Boolean, List<Phone>> partitioningByPhonesCompany = phoneStream4.collect(
                Collectors.partitioningBy(phone -> Objects.equals(phone.getCompany(), "Apple"))
                //grouping by boolean statement
        );

        for (var item : partitioningByPhonesCompany.entrySet()) { // iterating over the entry set
            System.out.println(item.getKey()); // getting a boolean result
            for (var phone : item.getValue()) { // iterating by Phone names
                System.out.println(phone.getName()); // print phone name
            }
            System.out.println();
        }
        System.out.println("\n---------------------");

        System.out.println("\n---------summing----------");

        Stream<Phone> phoneStream5 = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));

        Map<String, Integer> summingBy = phoneStream5.collect(
                Collectors.groupingBy(Phone::getCompany, Collectors.summingInt(Phone::getPrice))
        );

        summingBy.forEach((key, value) -> System.out.println(key + "-" + value));

        System.out.println("\n---------------------");

        System.out.println("\n----------minBy/maxBy----------");

        Stream<Phone> phoneStream6 = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));

        Map<String, Optional<Phone>> minOfPhonePrice = phoneStream6.collect(
                Collectors.groupingBy(Phone::getCompany, // group by phone company
                        Collectors.minBy(Comparator.comparing(Phone::getPrice))) // comparing phone price
        );
        minOfPhonePrice.forEach((key, value) -> System.out.println(key + "-" + value.get().getName()));

        System.out.println("\n---------------------");

        System.out.println("\n---------summarizing---------");

        Stream<Phone> phoneStream7 = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));

        Map<String, IntSummaryStatistics> priceSummary = phoneStream7.collect(
                Collectors.groupingBy(Phone::getCompany,
                        Collectors.summarizingInt(Phone::getPrice)));

        priceSummary.forEach((key, value) -> System.out.println(key + " - " + value.getAverage()));

        System.out.println("\n---------------------");

        System.out.println("\n---------mapping---------");

        Stream<Phone> phoneStream8 = Stream.of(new Phone("iPhone X", "Apple", 600),
                new Phone("Pixel 2", "Google", 500),
                new Phone("iPhone 8", "Apple", 450),
                new Phone("Galaxy S9", "Samsung", 440),
                new Phone("Galaxy S8", "Samsung", 340));

        Map<String, List<String>> mapPhonesByCompany = phoneStream8.collect(
                Collectors.groupingBy(Phone::getCompany,
                        Collectors.mapping(Phone::getName, Collectors.toList()))
        );

        mapPhonesByCompany.forEach((key, value) -> System.out.println(key + " - " + value));

        System.out.println("\n-----------------------");

        System.out.println("\n---------parallelism with arrays---------");

        int[] array = initializeArray(6);
        for (var i : array) {
            System.out.println(i);
        }

        System.out.println("\n-----------------------");

        System.out.println("\n-----------parallelSetAll----------");

        Phone[] phonesMassive = new Phone[]{new Phone("iPhone 8", 54000),
                new Phone("Pixel 2", 45000),
                new Phone("Samsung Galaxy S9", 40000),
                new Phone("Nokia 9", 32000)};

        Arrays.parallelSetAll(phonesMassive, index -> {
            phonesMassive[index].setPrice(phonesMassive[index].getPrice() - 10000);
            return phonesMassive[index];
        });
        for (var i : phonesMassive) {
            System.out.printf("\n%s - %d", i.getName(), i.getPrice());
        }

        System.out.println("\n-----------------------");

        System.out.println("\n----------parallelPrefix------------");

        int[] parallelPrefixNums = new int[]{1, 2, 3, 4, 5};

        Arrays.parallelPrefix(parallelPrefixNums, (x, y) -> x * y);
        for (var i : parallelPrefixNums) {
            System.out.println(i);
        }

    }

    private static int[] initializeArray(int size) {
        int[] values = new int[size];
        Arrays.parallelSetAll(values, i -> i * 10);
        return values;
    }

    record Person(String name, Gender gender) {}

    public enum Gender {
        MALE, FEMALE
    }

    @Data
    static class Phone {
        private String name;
        private String company;
        private int price;

        public Phone(String name, String company, int price) {
            this.name = name;
            this.company = company;
            this.price = price;
        }

        public Phone(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }
}
