package edu.patterns.structure_patterns.decorator;

public class ToppingDecorator implements Pizza {

    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }


    @Override
    public String getPizzaDescription() {
        return pizza.getPizzaDescription();
    }

    @Override
    public double getPizzaCost() {
        return pizza.getPizzaCost();
    }

    @Override
    public String toString() {
        return pizza.toString();
    }
}
