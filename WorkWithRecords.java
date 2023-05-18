package FirstStap;

public class WorkWithRecords {
    public static void main(String[] args) {
        RecordClass record = new RecordClass("Jerry", 12345);
        System.out.println(record.name());
    }
}

// Key word record create a class witch:
// may implement an interfaces,
// create getters, constructor,
// override equals, hashcode, toString.
// In Record class may do:
// create constants, non/static methods

// Constraints:
// extends only record classes,
// not have getters

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