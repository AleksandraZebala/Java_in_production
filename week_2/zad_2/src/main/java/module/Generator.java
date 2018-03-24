package module;
import com.google.gson.GsonBuilder;
import data.InputData;
import data.Item;
import data.OutputData;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class Generator {

    private static int getRandomInteger(Integer from, Integer to){
        return (int) (from + Math.random() * (to - from));
    }

    private static String getRandomDate(ZonedDateTime from, ZonedDateTime to){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        long time1 = Timestamp.from(from.toInstant()).getTime();
        long time2 = Timestamp.from(to.toInstant()).getTime();

        long randomTimestamp = time1 + (long) (Math.random() * (time2 - time1));
        Instant instant = Instant.ofEpochMilli(randomTimestamp);

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, from.getZone());
        return formatter.format(zonedDateTime);
    }

    private static ArrayList<Item> getItemsList(File file) throws IOException{

        ArrayList<Item> itemsList = new ArrayList<Item>();
        List<String> itemsFromFile = Files.readAllLines(file.toPath());

        itemsFromFile.forEach(line -> {
            String[] itemValues = line.split(",");
            Item item = new Item();
            item.name = itemValues[0].replace("\"", "");
            item.price = Float.parseFloat(itemValues[1]);
            itemsList.add(item);
        });

        return itemsList;
    }

    /*public static void saveToFile throws IOException(String JSONdata, String outDir){

        File output = new File(outDir);

        if(!output.exists())
            output.mkdir();

        PrintWriter out = new PrintWriter(output + "/output.json");
        out.println(jsonData);
        out.close();
    }*/

    public static String generateJSON(InputData input) throws IOException{

        ArrayList<Item> itemsList = getItemsList(input.itemsFile);

        ArrayList<OutputData> outs = new ArrayList<OutputData>();

        for (int i = 0; i < input.eventsCount; i++) {

            OutputData output = new OutputData();

            output.id = i;

            output.timestamp = getRandomDate(input.dateRange.from, input.dateRange.to);

            output.customer_id = getRandomInteger(input.customerIds.from, input.customerIds.to);

            ArrayList<Item> items = new ArrayList<Item>();
            int itemsCount = getRandomInteger(input.itemsCount.from, input.itemsCount.to);


            for(int j = 0; j < itemsCount; j++) {
                Item item = itemsList.get(j % itemsList.size());
                item.quantity = getRandomInteger(input.itemsQuantity.from, input.itemsQuantity.to);
                items.add(item);
            }

            output.items = items;

            output.sum = items.stream()
                    .mapToDouble(item -> item.quantity * item.price)
                    .sum();

            outs.add(output);
        }

        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(outs);
    }
}
