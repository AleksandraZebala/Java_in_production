import exceptions.WrongArgumentException;
import module.CommandParser;
import org.junit.Test;

public class WrongArgumentsTests {

    @Test(expected = WrongArgumentException.class)
    public void wrongCustomerIdTest1() throws Exception{
        String[] args = {"-customerIds", "djidji"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongCustomerIdTest2() throws Exception{
        String[] args = {"-customerIds", "555:j"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongCustomerIdTest3() throws Exception{
        String[] args = {"-customerIds", "87:2"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongCustomerIdTest4() throws Exception{
        String[] args = {"-customerIds", "c:c"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongCustomerIdTest5() throws Exception{
        String[] args = {"-customerIds", "j:5"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsCountTest1() throws Exception{
        String[] args = {"-itemsCount", "djidji"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsCountTest2() throws Exception{
        String[] args = {"-itemsCount", "555:j"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsCountTest3() throws Exception{
        String[] args = {"-itemsCount", "87:2"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsCountTest4() throws Exception{
        String[] args = {"-itemsCount", "c:c"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsCountTest5() throws Exception{
        String[] args = {"-itemsCount", "j:5"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsQuantityTest1() throws Exception{
        String[] args = {"-itemsQuantity", "djidji"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsQuantityTest2() throws Exception{
        String[] args = {"-itemsQuantity", "555:j"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsQuantityTest3() throws Exception{
        String[] args = {"-itemsQuantity", "87:2"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsQuantityTest4() throws Exception{
        String[] args = {"-itemsQuantity", "c:c"};
        CommandParser.parse(args);
    }

    @Test(expected = WrongArgumentException.class)
    public void wrongItemsQuantityTest5() throws Exception{
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
