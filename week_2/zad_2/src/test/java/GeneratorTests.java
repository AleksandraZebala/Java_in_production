import data.InputData;
import module.Generator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.nio.file.Files;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Generator.class)
public class GeneratorTests{

    @Test
    public void generatorTest() throws Exception{

        List<String> itemsFile = new ArrayList<String>();
        itemsFile.add("lizak, 2.30");

        File file = Mockito.mock(File.class);
        Mockito.when(file.toPath()).thenReturn(null);

        PowerMockito.mockStatic(Files.class);
        PowerMockito.when(Files.readAllLines(null)).thenReturn(itemsFile);

        Generator generator = new Generator();

        InputData inputData = new InputData();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        inputData.customerIds.from = 70;
        inputData.customerIds.to = 70;
        inputData.dateRange.from = ZonedDateTime.parse("2018-03-08T00:00:00.050-0100", formatter);
        inputData.dateRange.to = ZonedDateTime.parse("2018-03-08T00:00:00.050-0100", formatter);
        inputData.itemsCount.from = 1;
        inputData.itemsCount.to = 1;
        inputData.itemsQuantity.from = 9;
        inputData.itemsQuantity.to = 9;
        inputData.eventsCount = 1;
        inputData.itemsFile = file;

        String JSON = Generator.generate(inputData);

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
}