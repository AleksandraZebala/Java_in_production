import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mock;

public class ArgumentsTests {

    @Test
    public void noArgumentsTest() {
        String[] args = {};
        Main.main(args);
        Assert.assertEquals(Main.output, "File not found!");
    }

    @Test
    public void oneArgumentTest() {
        String[] args = {"-itemsFile", "items.csv"};
        Main.main(args);
        Assert.assertEquals(Main.output, "File generated");
    }

    @Test
    public void allArgumentTest() {
        String arg = "-customerIds 1:20 " +
                "-dateRange 2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100 " +
                "-itemsFile items.csv " +
                "-itemsCount 5:15 " +
                "-itemsQuantity 1:30 " +
                "-eventsCount 10 " +
                "-outDir ./output";

        String[] args = arg.split(" ");
        Main.main(args);
        Assert.assertEquals(Main.output, "File generated");
    }

    @Test
    public void wrongItemsFileTest() {
        String[] args = {"-itemsFile", "hoho.jpg"};
        Main.main(args);
        Assert.assertEquals(Main.output, "File not found!");
    }

    @Test
    public void wrongCustomerId1(){
        String[] args = {"-customerIds", "djidji"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongCustomerId2(){
        String[] args = {"-customerIds", "555:j"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongCustomerId3(){
        String[] args = {"-customerIds", "87:2"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongCustomerId4(){
        String[] args = {"-itemsFile", "items.csv", "-customerIds", "c:c"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongCustomerId5(){
        String[] args = {"-itemsFile", "items.csv", "-customerIds", "j:5"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }


    @Test
    public void wrongItemsCount1(){
        String[] args = {"-itemsFile", "items.csv", "-itemsCount", "djidji"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongItemsCount2(){
        String[] args = {"-itemsFile", "items.csv", "-itemsCount", "555:j"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongItemsCount3(){
        String[] args = {"-itemsFile", "items.csv", "-itemsCount", "87:2"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongItemsCount4(){
        String[] args = {"-itemsFile", "items.csv", "-itemsCount", "c:c"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongItemsCount5(){
        String[] args = {"-itemsFile", "items.csv", "-itemsCount", "j:5"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongItemsQuantity1(){
        String[] args = {"-itemsFile", "items.csv", "-itemsQuantity", "djidji"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongItemsQuantity2(){
        String[] args = {"-itemsFile", "items.csv", "-itemsQuantity", "555:j"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongItemsQuantity3(){
        String[] args = {"-itemsFile", "items.csv", "-itemsQuantity", "87:2"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongItemsQuantity4(){
        String[] args = {"-itemsFile", "items.csv", "-itemsQuantity", "c:c"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongItemsQuantity5(){
        String[] args = {"-itemsFile", "items.csv", "-itemsQuantity", "j:5"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongEventsCount(){
        String[] args = {"-itemsFile", "items.csv", "-eventsCount", "aaa"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }


    @Test
    public void wrongDate1(){
        String[] args = {"-itemsFile", "items.csv", "-dateRange", "haha"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }

    @Test
    public void wrongDate2(){
        String[] args = {"-itemsFile", "items.csv", "-dateRange",
                "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqtdf8"};
        Main.main(args);
        Assert.assertEquals(Main.output, "Wrong arguments!");
    }


}