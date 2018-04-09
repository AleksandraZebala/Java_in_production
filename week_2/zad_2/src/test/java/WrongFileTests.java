import exceptions.WrongFileException;
import module.CommandParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(CommandParser.class)
public class WrongFileTests {

    @Test(expected = WrongFileException.class)
    public void noFileTest() throws Exception{
        String[] args = {};
        CommandParser.parse(args);
    }

    /*@Test(expected = WrongFileException.class)
    public void notExistingFileTest() throws Exception{

        File file = new File("src/main/resources/items.csv");

        //File file = Mockito.mock(File.class);
        Mockito.when(file.exists()).thenReturn(Boolean.FALSE);

        String[] args = {"-itemsFile", "items.csv"};
        CommandParser.parse(args);
    }*/
}
