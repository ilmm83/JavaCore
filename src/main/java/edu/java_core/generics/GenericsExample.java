package edu.java_core.generics;

public class GenericsExample {
    public static void main(String[] args) {
        new ForPrintingThings<Integer>(123).printThing();

        var obj1 = new NumericFoo<Integer>(123);
        var obj2 = new NumericFoo<Double>(123.0);
        System.out.println(obj1.absEqual(obj2));
    }
}

record ForPrintingThings<T>(T value) {

    public void printThing() {
        System.out.println(value + " " + value.getClass().getName());
    }
}

record NumericFoo<T extends Number>(T num) {

    public void sqrt() {
        System.out.println(num.intValue() * num.doubleValue());
    }

    boolean absEqual(NumericFoo<? extends Number> numericFoo) {
        return (Math.abs(num.intValue()) == Math.abs(numericFoo.num.doubleValue()));
    }
}
