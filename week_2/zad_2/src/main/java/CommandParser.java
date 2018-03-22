import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CommandParser {

    public static InputData parse(String[] args) throws ParseException,
            WrongArgumentException, FileNotFoundException {

        Options options = new Options();
        options.addOption("customerIds", true, "customerIds");
        options.addOption("dateRange", true, "dateRange");
        options.addOption("itemsFile", true, "itemsFile");
        options.addOption("itemsCount", true, "itemsCount");
        options.addOption("itemsQuantity", true, "itemsQuantity");
        options.addOption("eventsCount", true, "eventsCount");
        options.addOption("outDir", true, "outDir");

        CommandLine cmd = new BasicParser().parse(options, args);
        InputData result = new InputData();

        //CUSTOMER IDS

        String customerIds = cmd.getOptionValue("customerIds");
        String[] arr;

        if(customerIds != null){
            if (!customerIds.contains(":"))
                throw new WrongArgumentException();

            arr = customerIds.split(":");

            if (!StringUtils.isNumeric(arr[0]) || !StringUtils.isNumeric(arr[1]))
                throw new WrongArgumentException();

            if(Integer.parseInt(arr[0]) > Integer.parseInt(arr[1]))
                throw new WrongArgumentException();

            result.customerIdsFrom = Integer.parseInt(arr[0]);
            result.customerIdsTo = Integer.parseInt(arr[1]);
        }
        else{
            result.customerIdsFrom = 1;
            result.customerIdsTo = 20;
        }

        // DATE RANGE

        String dateRange = cmd.getOptionValue("dateRange");

        if(dateRange != null) {

            if(dateRange.length() != 57)
                throw new WrongArgumentException();

            String from = dateRange.substring(0, 28);
            String to = dateRange.substring(29, 57);

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

            long time1 = Timestamp.from(ZonedDateTime.parse(from, format).toInstant()).getTime();
            long time2 = Timestamp.from(ZonedDateTime.parse(to, format).toInstant()).getTime();

            long randomTimestamp = time1 + (long) (Math.random() * (time2 - time1));

            Instant instant = Instant.ofEpochMilli(randomTimestamp);
            ZonedDateTime zonedDateTime = ZonedDateTime
                    .ofInstant(instant, ZonedDateTime.parse(from, format).getZone());

            result.date = format.format(zonedDateTime);
        }
        else {

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = LocalDateTime.now().format(format);
            result.date = date + "T00:00:00.000-0100:" + date + "T23:59:59.999-0100";
        }

        // ITEMS FILE

        String itemsFile = cmd.getOptionValue("itemsFile");

        File file = new File("src/main/java/"+itemsFile);

        if (!file.exists())
            throw new FileNotFoundException();

        result.itemsFile = file;

        // ITEMS COUNT

        String itemsCount = cmd.getOptionValue("itemsCount");
        String[] arr2;

        if(itemsCount != null){
            if (!itemsCount.contains(":"))
                throw new WrongArgumentException();

            arr2 = itemsCount.split(":");

            if (!StringUtils.isNumeric(arr2[0]) || !StringUtils.isNumeric(arr2[1]))
                throw new WrongArgumentException();

            if(Integer.parseInt(arr2[0]) > Integer.parseInt(arr2[1]))
                throw new WrongArgumentException();

            result.itemsCountFrom = Integer.parseInt(arr2[0]);
            result.itemsCountTo = Integer.parseInt(arr2[1]);
        }
        else{
            result.customerIdsFrom = 1;
            result.customerIdsTo = 5;
        }

        //ITEMS QUANTITY

        String itemsQuantity = cmd.getOptionValue("itemsQuantity");
        String[] arr3;

        if(itemsQuantity != null){
            if (!itemsQuantity.contains(":"))
                throw new WrongArgumentException();

            arr3 = itemsQuantity.split(":");

            if (!StringUtils.isNumeric(arr3[0]) || !StringUtils.isNumeric(arr3[1]))
                throw new WrongArgumentException();

            if(Integer.parseInt(arr3[0]) > Integer.parseInt(arr3[1]))
                throw new WrongArgumentException();

            result.itemsQuantityFrom = Integer.parseInt(arr3[0]);
            result.itemsQuantityTo = Integer.parseInt(arr3[1]);
        }
        else{
            result.customerIdsFrom = 1;
            result.customerIdsTo = 5;
        }

        // EVENTS COUNTS

        String eventsCount = cmd.getOptionValue("eventsCount");

        if(eventsCount != null){
            if(!StringUtils.isNumeric(eventsCount))
                throw new WrongArgumentException();

            result.eventsCount = Integer.parseInt(eventsCount);
        }
        else {
            result.eventsCount = 100;
        }

        // OUT DIR

        String outDir = cmd.getOptionValue("outDir");

        if(outDir != null)
            result.outDir = "build/"+outDir;
        else
            result.outDir = "build/";

        // RETURN

        return result;
    }
}