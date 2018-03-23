import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import junit.framework.Assert;

import data.InputData;
import module.CommandParser;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CommandParser.class)
public class CommandParserTests {

    private void fileExistsMock() throws Exception{

        File file = Mockito.mock(File.class);
        PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(file);
        Mockito.when(file.exists()).thenReturn(Boolean.TRUE);

    }

    @Test
    public void onlyItemFileArgumentTest() throws Exception{

        fileExistsMock();

        String[] args = {};
        InputData result = CommandParser.parse(args);

        Assert.assertEquals(1, result.customerIds.from);
        Assert.assertEquals(20, result.customerIds.to);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZonedDateTime zoned = ZonedDateTime.parse(result.date, formatter1);
        String date = formatter2.format(zoned);

        String expected = LocalDateTime.now().format(formatter2);

        Assert.assertEquals(expected, date);

        Assert.assertEquals(1, result.itemsCount.from);
        Assert.assertEquals(5, result.itemsCount.to);

        Assert.assertEquals(1, result.itemsQuantity.from);
        Assert.assertEquals(5, result.itemsQuantity.to);

        Assert.assertEquals(100, result.eventsCount);

        Assert.assertEquals(".", result.outDir);
    }

    @Test
    public void allArgumentsTest() throws Exception{

        fileExistsMock();

        String arg = "-customerIds 2:15 " +
                "-dateRange 2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100 " +
                "-itemsFile items.csv " +
                "-itemsCount 5:15 " +
                "-itemsQuantity 1:30 " +
                "-eventsCount 10 " +
                "-outDir ./output";
        String[] args = arg.split(" ");

        InputData result = CommandParser.parse(args);

        Assert.assertEquals(2, result.customerIds.from);
        Assert.assertEquals(15, result.customerIds.to);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZonedDateTime zoned = ZonedDateTime.parse(result.date, formatter1);
        String date = formatter2.format(zoned);

        Assert.assertEquals("2018-03-08", date);

        Assert.assertEquals(5, result.itemsCount.from);
        Assert.assertEquals(15, result.itemsCount.to);

        Assert.assertEquals(1, result.itemsQuantity.from);
        Assert.assertEquals(30, result.itemsQuantity.to);

        Assert.assertEquals(10, result.eventsCount);

        Assert.assertEquals("./output", result.outDir);
    }
}
