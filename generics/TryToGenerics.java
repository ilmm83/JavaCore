package FirstStap.generics;

public class TryToGenerics {
    public static void main(String[] args) {
        new ForPrintingThings(123).printThing();

        NumericFoo<Integer> obj1 = new NumericFoo<>(123);
        NumericFoo<Double> obj2 = new NumericFoo<>(123.0);
        System.out.println(obj1.absEqual(obj2));
    }
}

class ForPrintingThings<T> {
    final T value;

    public ForPrintingThings(T value) {
        this.value = value;
    }

    public void printThing() {
        System.out.println(value + " " + value.getClass().getName());
    }
}

class NumericFoo<T extends Number> {
    final T num;

    NumericFoo(T num) {
        this.num = num;
    }

    public void sqrt() {
        System.out.println(num.intValue() * num.doubleValue());
    }

    boolean absEqual(NumericFoo<? extends Number> numericFoo) {
        return (Math.abs(num.intValue()) == Math.abs(numericFoo.num.doubleValue()));
    }

}
