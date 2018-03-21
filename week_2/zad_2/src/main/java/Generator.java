import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class Generator {

    public static void generate (InputData input) throws IOException {

        ArrayList<Item> items = new ArrayList<Item>();

        List<String> itemsFromFile = Files.readAllLines(input.itemsFile.toPath());
        itemsFromFile.forEach(line -> {
            String[] itemValues = line.split(",");
            Item item = new Item();
            item.name = itemValues[0].replace("\"", "");
            item.quantity = ThreadLocalRandom.current().nextInt(input.customerIdsFrom, input.customerIdsTo);
            item.price = Float.parseFloat(itemValues[1]);
            items.add(item);
        });

        ArrayList<OutputData> outs = new ArrayList<OutputData>();

        for (int i = 0; i < input.eventsCount; i++) {
            OutputData output = new OutputData();
            output.id = i;
            output.timestamp = input.date;
            output.customer_id = ThreadLocalRandom.current().nextInt(input.customerIdsFrom, input.customerIdsTo);
            output.items = items;
            output.sum = items.stream().mapToDouble(item -> item.quantity * item.price).sum();
            outs.add(output);
        }

        String jsonData = new GsonBuilder().setPrettyPrinting().serializeNulls().create().toJson(outs);

        String output = input.outDir;
        PrintWriter out = new PrintWriter(output + "/output.json");
        out.println(jsonData);
        out.close();
    }
}
