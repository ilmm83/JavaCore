package edu.patterns.behavior_patterns.template_method;

public class ItalianHoagie extends Hoagie {

    private String[] meatUsed = {"Salami", "Pepperoni", "Capicola Ham"};
    private String[] cheeseUsed = {"Provolone"};
    private String[] veggiesUsed = {"Lettuce", "Tomatoes", "Onions", "Sweet Peppers"};
    private String[] condimentsUsed = {"Oil", "Vinegar"};


    @Override
    public void addCheese() {
        System.out.println("\nAdding the cheese: ");
        for (var cheese : cheeseUsed) {
            System.out.println(cheese + " ");
        }
    }

    @Override
    public void addMeat() {
        System.out.println("\nAdding the Meat: ");
        for (var meat : meatUsed) {
            System.out.println(meat + " ");
        }
    }

    @Override
    public void addVegetables() {
        System.out.println("\nAdding the Vegetables: ");
        for (var vegetable : veggiesUsed) {
            System.out.println(vegetable + " ");
        }
    }

    @Override
    public void addCondiments() {
        System.out.println("\nAdding the Condiments: ");
        for (var condiment : condimentsUsed) {
            System.out.println(condiment + " ");
        }
    }
}
