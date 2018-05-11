package data;
import java.math.BigDecimal;

public final class Item {
    private String name;
    private int quantity;
    private BigDecimal price;

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
