package edu.patterns.behavior_patterns.observer;

import java.text.DecimalFormat;

import static edu.patterns.behavior_patterns.observer.Stock.*;


public class GetTheStock implements Runnable {

    private int startTime;
    private Stock stock;
    private double price;
    private Subject stockGrabber;


    public GetTheStock(int startTimeInSeconds, Stock stock, double price, Subject stockGrabber) {
        this.startTime = startTimeInSeconds;
        this.stock = stock;
        this.price = price;
        this.stockGrabber = stockGrabber;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            try {
                Thread.sleep(startTime * 1_000L);
            } catch (InterruptedException e) {}

            var random = (Math.random() * (.06) - .03);
            var df = new DecimalFormat("#.##");

            price = Double.parseDouble(df.format(price + random));

            if (stock == IBM) ((StockGrabber) stockGrabber).setIbmPrice(price);
            if (stock == APPLE) ((StockGrabber) stockGrabber).setApplePrice(price);
            if (stock == GOOGLE) ((StockGrabber) stockGrabber).setGooglePrice(price);

            System.out.println("\n" + stock + ": " + price + " " + df.format(random) + "\n");
        }
    }
}
