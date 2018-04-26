import data.Item;
import exceptions.WrongFileException;
import module.FileWrapper;
import module.ItemsParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
        Item item = new Item();
        item.name = "lizak";
        item.price = (float) 2.30;
        expectedItemsList.add(item);

        Assert.assertEquals(expectedItemsList.get(0).name, ItemsParser.getItemsList(fileWrapper).get(0).name);
    }

    @Test(expected = WrongFileException.class)
    public void wrongFileTest() throws Exception {
        FileWrapper fileWrapper = Mockito.mock(FileWrapper.class);
        Mockito.when(fileWrapper.exists()).thenReturn(false);

        ItemsParser.getItemsList(fileWrapper);
    }

    @Test
    public void classTest(){
        ItemsParser itemsParser = new ItemsParser();
    }
}
