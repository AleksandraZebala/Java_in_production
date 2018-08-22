package data;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "transaction")
public class Transaction {

    private int id;
    private String timestamp;
    private int customer_id;
    @XmlElement(name = "item", type = Item.class)
    private List<Item> items = new ArrayList<Item>();
    private BigDecimal sum;

    public Transaction() {
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}