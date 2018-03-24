import module.CommandParser;
import module.Generator;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratorTests{

    @Test
    public void generatorTest() throws Exception{

        Generator generator = new Generator();

        String itemsFile = "mleko 3% 1l,2.30";

        String arg = "-customerIds 70:70 " +
                "-dateRange 2018-03-08T00:00:00.050-0100:2018-03-08T00:00:00.050-0100 " +
                "-itemsFile items.csv " +
                "-itemsCount 1:1 " +
                "-itemsQuantity 9:9 " +
                "-eventsCount 1 " +
                "-outDir ./output";

        String[] args = arg.split(" ");
        String JSON = Generator.generateJSON(CommandParser.parse(args));

        String expectedJSON = "[\n" +
                "  {\n" +
                "    \"id\": 0,\n" +
                "    \"timestamp\": \"2018-03-08T00:00:00.050-0100\",\n" +
                "    \"customer_id\": 70,\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"name\": \"mleko 3% 1l\",\n" +
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