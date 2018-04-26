import data.InputData;
import data.Item;
import module.Generator;
import module.Randomizer;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GeneratorTests{

    @Test
    public void generatorTest() throws Exception{

        ArrayList<Item> itemsList = new ArrayList<Item>();
        Item item = new Item();
        item.name = "lizak";
        item.price = (float) 2.30;
        itemsList.add(item);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        Randomizer randomizer = Mockito.mock(Randomizer.class);
        Mockito.when(randomizer.getRandomInteger(1, 100)).thenReturn(70);
        Mockito.when(randomizer.getRandomInteger(1, 3)).thenReturn(1);
        Mockito.when(randomizer.getRandomInteger(1, 10)).thenReturn(9);
        Mockito.when(randomizer.getRandomDate(ZonedDateTime.parse("2018-03-08T00:00:00.050-0100", formatter),
                ZonedDateTime.parse("2018-03-08T00:00:00.050-0100", formatter)))
                .thenReturn("2018-03-08T00:00:00.050-0100");

        InputData inputData = new InputData();

        inputData.customerIds.from = 1;
        inputData.customerIds.to = 100;
        inputData.dateRange.from = ZonedDateTime.parse("2018-03-08T00:00:00.050-0100", formatter);
        inputData.dateRange.to = ZonedDateTime.parse("2018-03-08T00:00:00.050-0100", formatter);
        inputData.itemsCount.from = 1;
        inputData.itemsCount.to = 3;
        inputData.itemsQuantity.from = 1;
        inputData.itemsQuantity.to = 10;
        inputData.eventsCount = 1;

        String JSON = Generator.generate(inputData, itemsList, randomizer);

        String expectedJSON = "[\n" +
                "  {\n" +
                "    \"id\": 0,\n" +
                "    \"timestamp\": \"2018-03-08T00:00:00.050-0100\",\n" +
                "    \"customer_id\": 70,\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"name\": \"lizak\",\n" +
                "        \"quantity\": 9,\n" +
                "        \"price\": 2.3\n" +
                "      }\n" +
                "    ],\n" +
                "    \"sum\": 20.69999885559082\n" +
                "  }\n" +
                "]";

        assertEquals(expectedJSON, JSON);
    }

    @Test
    public void classTest(){
        Generator generator = new Generator();
    }
}