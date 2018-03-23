import data.InputData;
import junit.framework.Assert;
import module.CommandParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CommandParser.class)
public class CommandParserTests {

    public void fileExistsMock() throws Exception{

        File file = Mockito.mock(File.class);
        PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(file);
        Mockito.when(file.exists()).thenReturn(Boolean.TRUE);

    }

    @Test
    public void noArgumentsTest() throws Exception {

        fileExistsMock();
        String[] args = {};
        InputData result = CommandParser.parse(args);

        Assert.assertEquals(1, result.customerIds.from);
        Assert.assertEquals(20, result.customerIds.to);
        Assert.assertEquals(1, result.itemsCount.from);
        Assert.assertEquals(5, result.itemsCount.to);
        Assert.assertEquals(1, result.itemsQuantity.from);
        Assert.assertEquals(5, result.itemsQuantity.to);
    }

    @Test
    public void allArgumentsTest() throws Exception {

        fileExistsMock();

        String arg = "-customerIds 2:15 " +
                "-dateRange 2018-03-08T00:00:00.000-0100:2018-03-08T00:00:00.000-0100 " +
                "-itemsFile items.csv " +
                "-itemsCount 5:15 " +
                "-itemsQuantity 1:30 " +
                "-eventsCount 10 " +
                "-outDir ./output";
        String[] args = arg.split(" ");

        InputData result = CommandParser.parse(args);

        Assert.assertEquals(2, result.customerIds.from);
        Assert.assertEquals(15, result.customerIds.to);
        Assert.assertEquals(5, result.itemsCount.from);
        Assert.assertEquals(15, result.itemsCount.to);
        Assert.assertEquals(1, result.itemsQuantity.from);
        Assert.assertEquals(30, result.itemsQuantity.to);

    }

}
