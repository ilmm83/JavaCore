package edu.patterns.behavior_patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class StockGrabber implements Subject {

    private List<Observer> observers;
    private double ibmPrice;
    private double applePrice;
    private double googlePrice;


    public StockGrabber() {
        this.observers = new ArrayList<>();
    }


    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unregister(Observer observer) {
        var id = observers.indexOf(observer);
        observers.remove(observer);

        System.out.println("\nObserver " + id + " has been removed successfully!\n");
    }

    @Override
    public void notifyObserver() {
        observers.forEach(observer -> observer.update(ibmPrice, applePrice, googlePrice));
    }


    public void setIbmPrice(double ibmPrice) {
        this.ibmPrice = ibmPrice;
        notifyObserver();
    }

    public void setApplePrice(double applePrice) {
        this.applePrice = applePrice;
        notifyObserver();
    }

    public void setGooglePrice(double googlePrice) {
        this.googlePrice = googlePrice;
        notifyObserver();
    }
}
