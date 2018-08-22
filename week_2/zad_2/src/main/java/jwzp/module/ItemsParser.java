package jwzp.module;

import jwzp.data.Item;
import jwzp.exceptions.WrongFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class ItemsParser {

    private static Logger logger = LogManager.getLogger(CommandParser.class);

    public ArrayList<Item> getItemsList(FileWrapper file) throws IOException, WrongFileException {

        logger.info("Charging items from file...");

        if (!file.exists()) {
            logger.info("File " + file + " does not exist");
            throw new WrongFileException();
        }

        ArrayList<Item> itemsList = new ArrayList<Item>();
        ArrayList<String> itemsFromFile = file.readAllLines();

        itemsFromFile.forEach(line -> {
            String[] itemValues = line.split(",");
            String name = itemValues[0].replace("\"", "");
            BigDecimal price = BigDecimal.valueOf(Float.parseFloat(itemValues[1]));
            Item item = new Item(name, price);
            itemsList.add(item);
        });

        logger.info("Charged items from file succesfully");
        return itemsList;
    }
}
