//@RunWith(MockitoJUnitRunner.class)
//public class GeneratorTests {

//    @Test
//    public void generatorTest() throws Exception{
//
//        ArrayList<Item> itemsList = new ArrayList<>();
//        String name = "lizak";
//        BigDecimal price = new BigDecimal("2.3");
//        Item item = new Item(name, price);
//        itemsList.add(item);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//
//        Randomizer randomizer = Mockito.mock(Randomizer.class);
//        Mockito.when(randomizer.getRandomInteger(1, 100)).thenReturn(70);
//        Mockito.when(randomizer.getRandomInteger(1, 3)).thenReturn(1);
//        Mockito.when(randomizer.getRandomInteger(1, 10)).thenReturn(9);
//        Mockito.when(randomizer.getRandomDate(ZonedDateTime.parse("2018-03-08T00:00:00.050-0100", formatter),
//                ZonedDateTime.parse("2018-03-08T00:00:00.050-0100", formatter)))
//                .thenReturn("2018-03-08T00:00:00.050-0100");
//
//
//        Range customerIds = new Range(1, 100);
//        Range dateRange = new Range(ZonedDateTime.parse("2018-03-08T00:00:00.050-0100", formatter),
//                ZonedDateTime.parse("2018-03-08T00:00:00.050-0100", formatter));
//        Range itemsCount = new Range(1, 3);
//        Range itemsQuantity = new Range(1, 10);
//        int eventsCount = 1;
//        String itemsFile = "test";
//        String outDir = "test";
//
//        InputData inputData = new InputData(customerIds, dateRange, itemsFile,
//                itemsCount, itemsQuantity, eventsCount, outDir);
//
//        ArrayList<Transaction> transactions = new Generator().generate(inputData, itemsList, randomizer);
//
//        String JSON = transactions.get(0);
//
//        String expectedJSON = "{\n" +
//                "  \"id\": 0,\n" +
//                "  \"timestamp\": \"2018-03-08T00:00:00.050-0100\",\n" +
//                "  \"customer_id\": 70,\n" +
//                "  \"items\": [\n" +
//                "    {\n" +
//                "      \"name\": \"lizak\",\n" +
//                "      \"quantity\": 9,\n" +
//                "      \"price\": 2.3\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"sum\": 20.70\n" +
//                "}";
//
//        assertEquals(expectedJSON, JSON);
//    }
//}