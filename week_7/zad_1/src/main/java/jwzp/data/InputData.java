package jwzp.data;
import java.time.ZonedDateTime;

public final class InputData {

    private Range<Integer> customerIds;
    private Range<ZonedDateTime> dateRange;
    private String itemsFile;
    private Range<Integer> itemsCount;
    private Range<Integer> itemsQuantity;
    private int eventsCount;
    private String outDir;

    private String broker;
    private String queue;
    private String topic;

    public InputData(Range<Integer> customerIds, Range<ZonedDateTime> dateRange, String itemsFile, Range<Integer> itemsCount, Range<Integer> itemsQuantity, int eventsCount, String outDir, String broker, String queue, String topic) {
        this.customerIds = customerIds;
        this.dateRange = dateRange;
        this.itemsFile = itemsFile;
        this.itemsCount = itemsCount;
        this.itemsQuantity = itemsQuantity;
        this.eventsCount = eventsCount;
        this.outDir = outDir;
        this.broker = broker;
        this.queue = queue;
        this.topic = topic;
    }



    public String getOutDir() { return outDir; }

    public Range<Integer> getCustomerIds() { return customerIds; }

    public Range<ZonedDateTime> getDateRange() { return dateRange; }

    public String getItemsFile() { return itemsFile; }

    public Range<Integer> getItemsCount() { return itemsCount; }

    public Range<Integer> getItemsQuantity() { return itemsQuantity; }

    public int getEventsCount() { return eventsCount; }

    public String getBroker() {
        return broker;
    }

    public String getQueue() {
        return queue;
    }

    public String getTopic() {
        return topic;
    }
}
