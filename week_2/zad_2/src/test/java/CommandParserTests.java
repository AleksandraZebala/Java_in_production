import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import data.InputData;
import module.CommandParser;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CommandParser.class)
public class CommandParserTests {

    @Test
    public void allArgumentsTest() throws Exception{

        CommandParser commandParser = new CommandParser();

        File file = Mockito.mock(File.class);
        PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(file);
        Mockito.when(file.exists()).thenReturn(Boolean.TRUE);

        String arg = "-customerIds 2:15 " +
                "-dateRange 2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100 " +
                "-itemsFile items.csv " +
                "-itemsCount 5:15 " +
                "-itemsQuantity 1:30 " +
                "-eventsCount 10 " +
                "-outDir ./output";

        String[] args = arg.split(" ");

        InputData result = CommandParser.parse(args);

        assertEquals(Integer.valueOf(2), result.customerIds.from);
        assertEquals(Integer.valueOf(15), result.customerIds.to);

        assertEquals(file, result.itemsFile);

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
    public void onlyItemFileArgumentTest() throws Exception{

        CommandParser commandParser = new CommandParser();

        File file = Mockito.mock(File.class);
        PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(file);
        Mockito.when(file.exists()).thenReturn(Boolean.TRUE);

        String[] args = {};
        InputData result = CommandParser.parse(args);

        assertEquals(Integer.valueOf(1), result.customerIds.from);
        assertEquals(Integer.valueOf(20), result.customerIds.to);

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
}
