package data;
import java.io.File;
import java.time.ZonedDateTime;

public class InputData {
    public Range<Integer> customerIds = new Range();
    public Range<ZonedDateTime> dateRange = new Range();
    public /*File*/ String itemsFile;
    public Range<Integer> itemsCount = new Range();
    public Range<Integer> itemsQuantity = new Range();
    public int eventsCount;
    public String outDir;
}
