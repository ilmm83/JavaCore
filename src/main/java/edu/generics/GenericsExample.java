package edu.generics;

import lombok.AllArgsConstructor;

public class GenericsExample {
    public static void main(String[] args) {
        // Testing Generic Class
        GenericClass<String> genericClass = new GenericClass<>();
        genericClass.setValue("Hello World");
        System.out.println("Generic Class returns : " + genericClass.getValue());

        // Testing Generic Interface
        GenericInterfaceImpl genericInterface = new GenericInterfaceImpl();
        System.out.println("Generic Interface returns : " + genericInterface.performAction("Hello Again!"));

        // Testing Generic Method
        genericDisplay("Test");
        genericDisplay(1);

        // Testing Bounded Type Parameters
        Bound<A> boundA = new Bound<>(new A());
        boundA.doRunTest();

        Bound<B> boundB = new Bound<>(new B());
        boundB.doRunTest();

        // Testing Generic Record
        GenericRecord<String> record = new GenericRecord<>("Hello from Generic Record class");
        record.printThing();
    }


    public static <T> void genericDisplay(T element) {
        System.out.println(element.getClass().getName() + " = " + element);
    }
}

class GenericClass<T> {

    private T t;


    public void setValue(T t) {
        this.t = t;
    }

    public T getValue() {
        return t;
    }
}

interface GenericInterface<T> {
    T performAction(T t);
}

class GenericInterfaceImpl implements GenericInterface<String> {

    @Override
    public String performAction(String s) {
        // You can implement your logic here...
        return s;
    }
}

@AllArgsConstructor
class Bound<T extends A> {

    private T objReference;


    public void doRunTest() {
        this.objReference.displayClass();
    }
}

class A {

    public void displayClass() {
        System.out.println("Inside super class A");
    }
}

class B extends A {

    public void displayClass() {
        System.out.println("Inside sub class B");
    }
}

record GenericRecord<T>(T value) {

    public void printThing() {
        System.out.println(value + " " + value.getClass().getName());
    }
}
