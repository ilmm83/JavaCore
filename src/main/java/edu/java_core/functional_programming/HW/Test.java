package edu.java_core.functional_programming.HW;

public class Test {
    public static void main(String[] args) {

        Candy candy = new Candy(60);
        System.out.println("Way: " + candy.getWay()
                + "\nprice: " + candy.getPriceForDelivery());
    }
}
