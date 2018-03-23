package module;
import data.InputData;
import data.Range;
import exceptions.WrongArgumentException;
import exceptions.WrongFileException;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandParser {

    private static Options newOptions() {
        Options options = new Options();
        options.addOption("customerIds", true, "customerIds");
        options.addOption("dateRange", true, "dateRange");
        options.addOption("itemsFile", true, "itemsFile");
        options.addOption("itemsCount", true, "itemsCount");
        options.addOption("itemsQuantity", true, "itemsQuantity");
        options.addOption("eventsCount", true, "eventsCount");
        options.addOption("outDir", true, "outDir");
        return options;
    }

    private static void parseRange(String in, Range result, String default_values)
            throws WrongArgumentException {

        if(in == null)
            in = default_values;

        if (!in.contains(":"))
            throw new WrongArgumentException();

        String[] stringValues = in.split(":");

        if (!StringUtils.isNumeric(stringValues[0]) || !StringUtils.isNumeric(stringValues[1]))
            throw new WrongArgumentException();

        int[] intValues = {Integer.parseInt(stringValues[0]), Integer.parseInt(stringValues[1])};

        if(intValues[0] > intValues[1])
            throw new WrongArgumentException();

        result.from = intValues[0];
        result.to = intValues[1];
    }

    private static String parseDate(String in) throws WrongArgumentException {

        if (in == null) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = LocalDateTime.now().format(format);
            return date + "T00:00:00.000-0100:" + date + "T23:59:59.999-0100";
        }

        if (in.length() != 57)
            throw new WrongArgumentException();

        String from = in.substring(0, 28);
        String to = in.substring(29, 57);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        long time1, time2;

        try {
            time1 = Timestamp.from(ZonedDateTime.parse(from, format).toInstant()).getTime();
            time2 = Timestamp.from(ZonedDateTime.parse(to, format).toInstant()).getTime();
        }
        catch(DateTimeParseException e){
            throw new WrongArgumentException();
        }

        long randTime = time1 + (long) (Math.random() * (time2 - time1));

        ZonedDateTime zoned = ZonedDateTime
                    .ofInstant(Instant.ofEpochMilli(randTime), ZonedDateTime.parse(from, format)
                    .getZone());

        return format.format(zoned);
    }

    private static File parseItemsFile (String in) throws WrongFileException{

        File file = new File("src/main/java/" + in);
        if (!file.exists())
            throw new WrongFileException();

        return file;
    }

    private static int parseEventsCount(String in, String def) throws WrongArgumentException{

        if (in == null)
            in = def;

        if (!StringUtils.isNumeric(in))
            throw new WrongArgumentException();

        return Integer.parseInt(in);
    }

    private static String parseOutDir(String in){
        if (in == null)
            return "output" ;

        return in;
    }

    public static InputData parse(String[] args)
            throws WrongArgumentException, WrongFileException, ParseException{

            CommandLine cmd = new BasicParser().parse(newOptions(), args);
            InputData result = new InputData();

            String customerIds = cmd.getOptionValue("customerIds");
            parseRange(customerIds, result.customerIds, "1:20");

            String itemsQuantity = cmd.getOptionValue("itemsQuantity");
            parseRange(itemsQuantity, result.itemsQuantity, "1:5");

            String itemsCount = cmd.getOptionValue("itemsCount");
            parseRange(itemsCount, result.itemsCount, "1:5");

            String dateRange = cmd.getOptionValue("dateRange");
            result.date = parseDate(dateRange);

            String eventsCount = cmd.getOptionValue("eventsCount");
            result.eventsCount = parseEventsCount(eventsCount, "100");

            String outDir = cmd.getOptionValue("outDir");
            result.outDir = parseOutDir(outDir);

            String itemsFile = cmd.getOptionValue("itemsFile");
            result.itemsFile = parseItemsFile(itemsFile);

            return result;
    }
}