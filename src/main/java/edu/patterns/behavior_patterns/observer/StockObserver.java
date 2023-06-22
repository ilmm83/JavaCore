package edu.patterns.behavior_patterns.observer;

public class StockObserver implements Observer {

    private double ibmPrice;
    private double applePrice;
    private double googlePrice;
    private int observerID;
    private Subject stockGrabber;

    private static int observersIDTracker = 0;


    public StockObserver(Subject stockGrabber) {
        this.stockGrabber = stockGrabber;
        observerID = ++observersIDTracker;

        System.out.println("New Observer " + observerID + "\n");
    }

    @Override
    public void update(double ibmPrice, double applePrice, double googlePrice) {
        this.ibmPrice = ibmPrice;
        this.applePrice = applePrice;
        this.googlePrice = googlePrice;

        printThePrices();
    }

    public void printThePrices() {
        System.out.printf("Observer - %d: \nIBM Price - %s\nApple Price - %s\nGoogle Price - %s\n\n"
                , observerID, ibmPrice, applePrice, googlePrice);
    }
}
