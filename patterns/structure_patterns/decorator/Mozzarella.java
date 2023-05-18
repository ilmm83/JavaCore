package FirstStap.patterns.structure_patterns.decorator;

public class Mozzarella extends ToppingDecorator {

    public Mozzarella(Pizza pizza) {
        super(pizza);
    }


    @Override
    public String getPizzaDescription() {
        return super.getPizzaDescription() + ", Mozzarella";
    }

    @Override
    public double getPizzaCost() {
        return super.getPizzaCost() + .5;
    }

    @Override
    public String toString() {
        return "Pizza: \n"
                + " " + getPizzaDescription()
                + "\n " + getPizzaCost();
    }
}
