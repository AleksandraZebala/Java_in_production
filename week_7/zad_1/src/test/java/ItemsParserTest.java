import jwzp.data.Item;
import jwzp.exceptions.WrongFileException;
import jwzp.module.FileWrapper;
import jwzp.module.ItemsParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ItemsParserTest {

    @Test
    public void rightFileTest() throws Exception{

        ArrayList<String> itemsFromFile = new ArrayList<String>();
        itemsFromFile.add("lizak, 2.30");

        FileWrapper fileWrapper = Mockito.mock(FileWrapper.class);
        Mockito.when(fileWrapper.exists()).thenReturn(true);
        Mockito.when(fileWrapper.readAllLines()).thenReturn(itemsFromFile);

        ArrayList<Item> expectedItemsList = new ArrayList<Item>();
        String name = "lizak";
        BigDecimal price = BigDecimal.valueOf((float) 2.30);
        Item item = new Item(name, price);
        expectedItemsList.add(item);

        Assert.assertEquals(expectedItemsList.get(0).getName(),
                new ItemsParser().getItemsList(fileWrapper).get(0).getName());
    }

    @Test(expected = WrongFileException.class)
    public void wrongFileTest() throws Exception {
        FileWrapper fileWrapper = Mockito.mock(FileWrapper.class);
        Mockito.when(fileWrapper.exists()).thenReturn(false);

        new ItemsParser().getItemsList(fileWrapper);
    }

    @Test
    public void classTest(){
        ItemsParser itemsParser = new ItemsParser();
    }
}
