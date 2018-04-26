import data.InputData;
import module.CommandParser;
import exceptions.WrongArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import static org.junit.Assert.*;

public class CommandParserTests {

    @Test
    public void allRightArgumentsTest() throws Exception {

        String arg = "-customerIds 2:15 " +
                "-dateRange 2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100 " +
                "-itemsFile test.test " +
                "-itemsCount 5:15 " +
                "-itemsQuantity 1:30 " +
                "-eventsCount 10 " +
                "-outDir ./output";

        String[] args = arg.split(" ");

        InputData result = CommandParser.parse(args);

        assertEquals(Integer.valueOf(2), result.customerIds.from);
        assertEquals(Integer.valueOf(15), result.customerIds.to);

        assertEquals("test.test", result.itemsFile);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        String from = formatter.format(result.dateRange.from);
        String to = formatter.format(result.dateRange.to);

        assertEquals("2018-03-08T00:00:00.000-0100", from);
        assertEquals("2018-03-08T23:59:59.999-0100", to);

        assertEquals(Integer.valueOf(5), result.itemsCount.from);
        assertEquals(Integer.valueOf(15), result.itemsCount.to);

        assertEquals(Integer.valueOf(1), result.itemsQuantity.from);
        assertEquals(Integer.valueOf(30), result.itemsQuantity.to);

        assertEquals(10, result.eventsCount);

        assertEquals("./output", result.outDir);
    }

    @Test
    public void onlyItemFileArgumentTest() throws Exception {

        String[] args = {"-itemsFile", "test.test"};

        InputData result = CommandParser.parse(args);

        assertEquals(Integer.valueOf(1), result.customerIds.from);
        assertEquals(Integer.valueOf(20), result.customerIds.to);

        assertEquals("test.test", result.itemsFile);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        String from = formatter.format(result.dateRange.from);
        String to = formatter.format(result.dateRange.to);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = LocalDateTime.now().format(formatter2);
        String expectedFrom = now + "T00:00:00.000-0100";
        String expectedTo = now + "T23:59:59.999-0100";

        assertEquals(expectedFrom, from);
        assertEquals(expectedTo, to);

        assertEquals(Integer.valueOf(1), result.itemsCount.from);
        assertEquals(Integer.valueOf(5), result.itemsCount.to);

        assertEquals(Integer.valueOf(1), result.itemsQuantity.from);
        assertEquals(Integer.valueOf(5), result.itemsQuantity.to);

        assertEquals(100, result.eventsCount);

        assertEquals(".", result.outDir);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongCustomerIdTest() throws Exception{
        String[] args = {"-customerIds", "djidji"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongCustomerIdTest2() throws Exception{
        String[] args = {"-customerIds", "j:5:7"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsCountTest() throws Exception{
        String[] args = {"-itemsCount", "555:j"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsCountTest2() throws Exception{
        String[] args = {"-itemsCount", "c:c"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsQuantityTest1() throws Exception{
        String[] args = {"-itemsQuantity", "87:2"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsQuantityTest2() throws Exception{
        String[] args = {"-itemsQuantity", "j:5"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongEventsCountTest() throws Exception{
        String[] args = {"-eventsCount", "aaa"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongDateTest1() throws Exception{
        String[] args = {"-dateRange", "haha"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongDateTest2() throws Exception{
        String[] args = {"-dateRange", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqtdf8"};
        CommandParser.parse(args);
    }

    @Test
    public void classTest(){
        CommandParser commandParser = new CommandParser();
    }
}
