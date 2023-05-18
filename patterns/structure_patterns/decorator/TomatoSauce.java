package FirstStap.patterns.structure_patterns.decorator;

public class TomatoSauce extends ToppingDecorator {

    public TomatoSauce(Pizza pizza) {
        super(pizza);
    }


    @Override
    public String getPizzaDescription() {
        return super.getPizzaDescription() + ", Tomato sauce";
    }

    @Override
    public double getPizzaCost() {
        return super.getPizzaCost() + .1;
    }

    @Override
    public String toString() {
        return "Pizza: \n"
                + " " + getPizzaDescription()
                + "\n " + getPizzaCost();
    }
}
