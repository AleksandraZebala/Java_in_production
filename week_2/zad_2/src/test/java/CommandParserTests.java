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

        InputData result = new CommandParser().parse(args);

        assertEquals(Integer.valueOf(2), result.getCustomerIds().getFrom());
        assertEquals(Integer.valueOf(15), result.getCustomerIds().getTo());

        assertEquals("test.test", result.getItemsFile());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        String from = formatter.format(result.getDateRange().getFrom());
        String to = formatter.format(result.getDateRange().getTo());

        assertEquals("2018-03-08T00:00:00.000-0100", from);
        assertEquals("2018-03-08T23:59:59.999-0100", to);

        assertEquals(Integer.valueOf(5), result.getItemsCount().getFrom());
        assertEquals(Integer.valueOf(15), result.getItemsCount().getTo());

        assertEquals(Integer.valueOf(1), result.getItemsQuantity().getFrom());
        assertEquals(Integer.valueOf(30), result.getItemsQuantity().getTo());

        assertEquals(10, result.getEventsCount());

        assertEquals("./output", result.getOutDir());
    }

    @Test
    public void onlyItemFileArgumentTest() throws Exception {

        String[] args = {"-itemsFile", "test.test"};

        InputData result = new CommandParser().parse(args);

        assertEquals(Integer.valueOf(1), result.getCustomerIds().getFrom());
        assertEquals(Integer.valueOf(20), result.getCustomerIds().getTo());

        assertEquals("test.test", result.getItemsFile());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        String from = formatter.format(result.getDateRange().getFrom());
        String to = formatter.format(result.getDateRange().getTo());

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = LocalDateTime.now().format(formatter2);
        String expectedFrom = now + "T00:00:00.000-0100";
        String expectedTo = now + "T23:59:59.999-0100";

        assertEquals(expectedFrom, from);
        assertEquals(expectedTo, to);

        assertEquals(Integer.valueOf(1), result.getItemsCount().getFrom());
        assertEquals(Integer.valueOf(5), result.getItemsCount().getTo());

        assertEquals(Integer.valueOf(1), result.getItemsQuantity().getFrom());
        assertEquals(Integer.valueOf(5), result.getItemsQuantity().getTo());

        assertEquals(100, result.getEventsCount());

        assertEquals(".", result.getOutDir());
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongCustomerIdTest() throws Exception{
        String[] args = {"-customerIds", "djidji"};
        new CommandParser().parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongCustomerIdTest2() throws Exception{
        String[] args = {"-customerIds", "j:5:7"};
        new CommandParser().parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsCountTest() throws Exception{
        String[] args = {"-itemsCount", "555:j"};
        new CommandParser().parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsCountTest2() throws Exception{
        String[] args = {"-itemsCount", "c:c"};
        new CommandParser().parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsQuantityTest1() throws Exception{
        String[] args = {"-itemsQuantity", "87:2"};
        new CommandParser().parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsQuantityTest2() throws Exception{
        String[] args = {"-itemsQuantity", "j:5"};
        new CommandParser().parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongEventsCountTest() throws Exception{
        String[] args = {"-eventsCount", "aaa"};
        new CommandParser().parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongDateTest1() throws Exception{
        String[] args = {"-dateRange", "haha"};
        new CommandParser().parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongDateTest2() throws Exception{
        String[] args = {"-dateRange", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqtdf8"};
        new CommandParser().parse(args);
    }

    @Test
    public void classTest(){
        CommandParser commandParser = new CommandParser();
    }
}
