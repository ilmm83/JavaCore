package FirstStap.patterns.structure_patterns.decorator;

public class PizzaMakerApplication {
    public static void main(String[] args) {
        Pizza basicPizza = new TomatoSauce(new Mozzarella(new PlainPizza(new ThinDough())));
        Pizza basicPizzaWithoutSauce = new Mozzarella(new PlainPizza(new ThinDough()));

        System.out.println(basicPizza);
        System.out.println(basicPizzaWithoutSauce);
    }
}
