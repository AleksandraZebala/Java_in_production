import exceptions.WrongArgumentException;
import module.CommandParser;
import org.junit.Test;

public class WrongArgumentsTests {

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
}
