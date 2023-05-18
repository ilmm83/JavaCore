package FirstStap.patterns.behavior_patterns.observer;

public class GrabStocksApplication {
    public static void main(String[] args) {

        var stockGrabber = new StockGrabber();

        var observer1 = new StockObserver(stockGrabber);
        stockGrabber.register(observer1);

        var observer2 = new StockObserver(stockGrabber);
        stockGrabber.register(observer2);

        stockGrabber.setApplePrice(20_000);
        stockGrabber.setIbmPrice(2_000);
        stockGrabber.setGooglePrice(5_000);

        stockGrabber.unregister(observer2);

        stockGrabber.setGooglePrice(50_000);


        var getIBM = new GetTheStock(2, Stock.IBM, 200, stockGrabber);
        var getApple = new GetTheStock(2, Stock.APPLE, 2_000, stockGrabber);
        var getGoogle = new GetTheStock(2, Stock.GOOGLE, 500, stockGrabber);

        new Thread(getIBM).start();
        new Thread(getApple).start();
        new Thread(getGoogle).start();
    }
}
