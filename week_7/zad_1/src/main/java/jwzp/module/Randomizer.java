package jwzp.module;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Randomizer {

    public int getRandomInteger(Integer from, Integer to){

        return (int) (from + Math.random() * (to - from));
    }

    public String getRandomDate(ZonedDateTime from, ZonedDateTime to){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        long time1 = Timestamp.from(from.toInstant()).getTime();
        long time2 = Timestamp.from(to.toInstant()).getTime();

        long randomTimestamp = time1 + (long) (Math.random() * (time2 - time1));
        Instant instant = Instant.ofEpochMilli(randomTimestamp);

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, from.getZone());
        return formatter.format(zonedDateTime);
    }
}
