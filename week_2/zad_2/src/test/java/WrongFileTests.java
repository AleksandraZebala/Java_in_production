import exceptions.WrongFileException;
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
public class WrongFileTests {

    @Test(expected = WrongFileException.class)
    public void noFileTest() throws Exception{
        String[] args = {};
        CommandParser.parse(args);
    }

    @Test(expected = WrongFileException.class)
    public void notExistingFileTest() throws Exception{

        File file = Mockito.mock(File.class);
        PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(file);
        Mockito.when(file.exists()).thenReturn(Boolean.FALSE);

        String[] args = {"-itemsFile", "items.csv"};
        CommandParser.parse(args);
    }
}
