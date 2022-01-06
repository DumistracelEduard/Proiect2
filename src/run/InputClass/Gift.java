package run.InputClass;

public class Gift {
    private final String productName;
    private final double price;
    private final String category;


    public Gift(final String productName, final double price,
                final String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public final String getProductName() {
        return productName;
    }

    public final double getPrice() {
        return price;
    }

    public final String getCategory() {
        return category;
    }
}
