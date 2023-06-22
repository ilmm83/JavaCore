package edu.record;

public class RecordsExample {
    public static void main(String[] args) {
        RecordClass record = new RecordClass("Jerry", 12345);
        System.out.println(record.name());
    }
}

// Key word record create a class which:
// may implement an interfaces,
// create getters, constructor and final fields,
// override equals, hashcode, toString.

// In Record class may do:
// create constants, non/static methods

// Constraints:
// extends only record classes,
// have no setters

record RecordClass(String name, int pass) {

    public static final String DEFAULT_VALUE = "Yep";

    // may override the constructor with additions
    public RecordClass {
        if (pass < 0) {
            throw new IllegalStateException();
        }
    }

    public static void printAny() {
        System.out.println("Any");
    }

    public String nameToUpperCase() {
        return name.toUpperCase();
    }
}