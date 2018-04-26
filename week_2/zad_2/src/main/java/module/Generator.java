package module;

import data.InputData;
import data.Item;
import data.JSONData;
import exceptions.WrongFileException;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {

    public static String generate(InputData input, ArrayList<Item> itemsList, Randomizer randomizer)
            throws IOException, WrongFileException {

        ArrayList<JSONData> outs = new ArrayList<JSONData>();

        for (int i = 0; i < input.eventsCount; i++) {

            JSONData output = new JSONData();

            output.id = i;

            output.timestamp = randomizer.getRandomDate(input.dateRange.from, input.dateRange.to);

            output.customer_id = randomizer.getRandomInteger(input.customerIds.from, input.customerIds.to);

            ArrayList<Item> items = new ArrayList<Item>();
            int itemsCount = randomizer.getRandomInteger(input.itemsCount.from, input.itemsCount.to);

            for(int j = 0; j < itemsCount; j++) {
                Item item = itemsList.get(j % itemsList.size());
                item.quantity = randomizer.getRandomInteger(input.itemsQuantity.from, input.itemsQuantity.to);
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
