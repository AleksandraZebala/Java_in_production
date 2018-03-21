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

}