import onlinemarket.OnlineMarket;

public class OnlineMarketApp {
    public static void main(String[] args) {
        OnlineMarket onlineMarket = new OnlineMarket();

        System.out.println("Total value of products: " + onlineMarket.calculateTotalValue());
        System.out.println("CEO: " + onlineMarket.getCeo());
        onlineMarket.getProducts().forEach(product -> System.out.println("Product: " + product));
    }

}
