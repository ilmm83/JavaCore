package edu.patterns.behavior_patterns.template_method;

public class SandwichSculptorApplication {
    public static void main(String[] args) {
        System.out.println("----Making Italian Hoagie----");

        Hoagie italianHoagie = new ItalianHoagie();
        italianHoagie.makeSandwich();

        System.out.println("\n\n ----Making Veggie Hoagie----");

        Hoagie veggiHoagie = new VeggieHoagie();
        veggiHoagie.makeSandwich();
    }
}
