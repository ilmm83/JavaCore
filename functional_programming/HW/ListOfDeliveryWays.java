package FirstStap.functional_programming.HW;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum ListOfDeliveryWays {

    ON_RAILS(BigDecimal.valueOf(1000)),
    NAUTICAL(BigDecimal.valueOf(1500)),
    AIR(BigDecimal.valueOf(2000));

    private BigDecimal price;

    ListOfDeliveryWays(BigDecimal price) {
        this.price = price;
    }
}
