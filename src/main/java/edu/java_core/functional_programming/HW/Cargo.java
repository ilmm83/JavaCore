package edu.java_core.functional_programming.HW;


public class Cargo implements Delivery {

    private CargoCapability capability;
    private ListOfDeliveryWays way;

    private void getCapability(int weigh) {
        if (weigh <= 50)
            capability = CargoCapability.SMALL;
        else if (weigh <= 100) {
            capability = CargoCapability.MEDIUM;
        } else {
            capability = CargoCapability.BIG;
        }
    }

    private void getOptimalTransport() {
        switch (capability){
            case SMALL -> way = ListOfDeliveryWays.ON_RAILS;
            case MEDIUM -> way = ListOfDeliveryWays.AIR;
            case BIG -> way = ListOfDeliveryWays.NAUTICAL;
        }
    }

    @Override
    public ListOfDeliveryWays getOptimalWay(int weigh) {
        getCapability(weigh);
        getOptimalTransport();
        return way;
    }
}
