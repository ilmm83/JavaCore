package edu.functional_programming.HW;

import java.math.BigDecimal;

public class Candy {

    private final Cargo cargo = new Cargo();
    private final ListOfDeliveryWays way;
    private final BigDecimal priceForDelivery;

    public Candy(int weigh) {
        way = cargo.getOptimalWay(weigh);
        priceForDelivery = way.getPrice();
    }

    public ListOfDeliveryWays getWay() {
        return way;
    }

    public BigDecimal getPriceForDelivery() {
        return priceForDelivery;
    }
}
