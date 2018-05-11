package data;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Transaction {
    private int id;
    private String timestamp;
    private int customer_id;
    private ArrayList<Item> items = new ArrayList<Item>();
    private BigDecimal sum;

    public Transaction(int id, String timestamp, int customer_id, ArrayList<Item> items, BigDecimal sum) {
        this.id = id;
        this.timestamp = timestamp;
        this.customer_id = customer_id;
        this.items = items;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public BigDecimal getSum() {
        return sum;
    }
}