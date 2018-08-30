import jwzp.module.Randomizer;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RandomizerTests {

    @Test
    public void openIntegerRangeTest(){
        Randomizer randomizer = new Randomizer();
        Integer random = randomizer.getRandomInteger(0, 500);
        Assert.assertTrue(random >= 0 && random <= 500);
    }

    @Test
    public void closeIntegerRangeTest(){
        Randomizer randomizer = new Randomizer();
        Integer random = randomizer.getRandomInteger(22, 22);
        Assert.assertTrue(random == 22);
    }

    @Test
    public void ZoneDateRangeTest(){

        Randomizer randomizer = new Randomizer();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String result = randomizer.getRandomDate(ZonedDateTime.parse("2018-03-08T00:00:00.000-0100", formatter),
                ZonedDateTime.parse("2018-03-08T00:00:00.000-0100", formatter));
        String expected = "2018-03-08T00:00:00.000-0100";

        Assert.assertEquals(expected, result);
    }
}
