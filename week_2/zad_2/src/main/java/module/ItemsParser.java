package module;

import data.Item;
import exceptions.WrongFileException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ItemsParser {

    public ArrayList<Item> getItemsList(FileWrapper file) throws IOException, WrongFileException {

        if (!file.exists())
            throw new WrongFileException();

        ArrayList<Item> itemsList = new ArrayList<Item>();
        ArrayList<String> itemsFromFile = file.readAllLines();

        itemsFromFile.forEach(line -> {
            String[] itemValues = line.split(",");
            String name = itemValues[0].replace("\"", "");
            BigDecimal price = BigDecimal.valueOf(Float.parseFloat(itemValues[1]));
            Item item = new Item(name, price);
            itemsList.add(item);
        });

        return itemsList;
    }
}
