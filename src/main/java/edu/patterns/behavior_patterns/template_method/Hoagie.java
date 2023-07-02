package edu.patterns.behavior_patterns.template_method;

public abstract class Hoagie {

    final void makeSandwich() {
        cutBun();

        if (isCustomerWantsMeat()) {
            addMeat();
        }
        if (isCustomerWantsCheese()) {
            addCheese();
        }
        if (isCustomerWantsVegetables()) {
            addVegetables();
        }
        if (isCustomerWantsCondiments()) {
            addCondiments();
        }

        wrapTheHoagie();
    }

    public void cutBun() {
        System.out.println("The Hoagie is Cut");
    }

    public void wrapTheHoagie() {
        System.out.println("Wrap the Hoagie");
    }

    protected abstract void addMeat();
    protected abstract void addCheese();
    protected abstract void addVegetables();
    protected abstract void addCondiments();

    protected boolean isCustomerWantsCondiments() {
        return true;
    }

    protected boolean isCustomerWantsVegetables() {
        return true;
    }

    protected boolean isCustomerWantsCheese() {
        return true;
    }

    protected boolean isCustomerWantsMeat() {
        return true;
    }
}
