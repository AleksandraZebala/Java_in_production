package jwzp.module;

import jwzp.data.InputData;
import jwzp.data.Item;
import jwzp.data.Transaction;
import jwzp.exceptions.WrongFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class Generator {

    private ItemsParser itemsParser;

    private Randomizer randomizer;

    public Generator(ItemsParser itemsParser, Randomizer randomizer) {
        this.randomizer = randomizer;
        this.itemsParser = itemsParser;
    }

    private static final Logger logger = LogManager.getLogger(CommandParser.class);

    public ArrayList<Transaction> generate(InputData input, FileWrapper fileWrapper)
            throws IOException, WrongFileException {

        ArrayList<Item> itemsList = itemsParser.getItemsList(fileWrapper);
        logger.info("Starting generating transactions...");

        ArrayList<Transaction> outs = new ArrayList<>();

        for (int i = 0; i < input.getEventsCount(); i++) {

            logger.info("Generating transaction number " + i);
            int id = i;

            String timestamp = randomizer.getRandomDate(input.getDateRange().getFrom(),
                    input.getDateRange().getTo());

            int customer_id = randomizer.getRandomInteger(input.getCustomerIds().getFrom(),
                    input.getCustomerIds().getTo());

            ArrayList<Item> items = new ArrayList<Item>();
            int itemsCount = randomizer.getRandomInteger(input.getItemsCount().getFrom(),
                    input.getItemsCount().getTo());

            for(int j = 0; j < itemsCount; j++) {
                Item item = itemsList.get(j % itemsList.size());
                item.setQuantity(randomizer.getRandomInteger(input.getItemsQuantity().getFrom(),
                        input.getItemsQuantity().getTo()));
                items.add(item);
            }

            BigDecimal sum = items.stream()
                    .map(item -> BigDecimal.valueOf((double)item.getQuantity()).multiply(item.getPrice()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            Transaction transaction = new Transaction(id, timestamp, customer_id, items, sum);

            outs.add(transaction);
        }

        return outs;
    }
}
