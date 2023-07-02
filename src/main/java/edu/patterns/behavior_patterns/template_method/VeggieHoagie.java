package edu.patterns.behavior_patterns.template_method;

public class VeggieHoagie extends Hoagie {

    private String[] veggiesUsed = {"Lettuce", "Tomatoes", "Onions", "Sweet Peppers"};
    private String[] condimentsUsed = {"Oil", "Vinegar"};


    @Override
    protected boolean isCustomerWantsCheese() {
        return false;
    }

    @Override
    protected boolean isCustomerWantsMeat() {
        return false;
    }

    @Override
    public void addCheese() {}

    @Override
    public void addMeat() {}

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
