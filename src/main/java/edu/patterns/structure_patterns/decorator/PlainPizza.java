package edu.patterns.structure_patterns.decorator;

public class PlainPizza implements Pizza {

    protected Dough dough;


    public PlainPizza(Dough dough) {
        this.dough = dough;
    }

    @Override
    public String getPizzaDescription() {
        return dough.getDoughDescription();
    }

    @Override
    public double getPizzaCost() {
        return 4.0;
    }

    @Override
    public String toString() {
        return "Pizza: \n"
                + " " + getPizzaDescription()
                + "\n " + getPizzaCost();
    }
}
