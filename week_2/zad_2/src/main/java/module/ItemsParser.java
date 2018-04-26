package module;

import data.Item;
import exceptions.WrongFileException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemsParser {

    public static ArrayList<Item> getItemsList(FileWrapper file) throws IOException, WrongFileException {

        if (!file.exists())
            throw new WrongFileException();

        ArrayList<Item> itemsList = new ArrayList<Item>();
        ArrayList<String> itemsFromFile = file.readAllLines();

        itemsFromFile.forEach(line -> {
            String[] itemValues = line.split(",");
            Item item = new Item();
            item.name = itemValues[0].replace("\"", "");
            item.price = Float.parseFloat(itemValues[1]);
            itemsList.add(item);
        });

        return itemsList;
    }
}
