package module;

import data.InputData;
import data.Range;
import exceptions.WrongArgumentException;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class CommandParser {

    private Options newOptions() {
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

    private Range<Integer> parseRange(String in, String default_values)
            throws WrongArgumentException {

        if(in == null)
            in = default_values;

        if (!in.contains(":"))
            throw new WrongArgumentException();

        String[] stringValues = in.split(":");

        if (stringValues.length != 2)
            throw new WrongArgumentException();

        if (!StringUtils.isNumeric(stringValues[0]) || !StringUtils.isNumeric(stringValues[1]))
            throw new WrongArgumentException();

        int[] intValues = {Integer.parseInt(stringValues[0]), Integer.parseInt(stringValues[1])};

        if(intValues[0] > intValues[1])
            throw new WrongArgumentException();

        return new Range(intValues[0], intValues[1]);
    }

    private Range<ZonedDateTime> parseDateRange(String in) throws WrongArgumentException {

        if (in == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            in = LocalDateTime.now().format(formatter);

            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            ZonedDateTime from = ZonedDateTime.parse(in + "T00:00:00.000-0100", formatter2);
            ZonedDateTime to = ZonedDateTime.parse(in + "T23:59:59.999-0100", formatter2);
            return new Range(from, to);
        }

        if (in.length() != 57)
            throw new WrongArgumentException();

        String from = in.substring(0, 28);
        String to = in.substring(29, 57);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        try {
            Range result = new Range(ZonedDateTime.parse(from, formatter), ZonedDateTime.parse(to, formatter));
            return result;
        } catch(DateTimeParseException e){
            throw new WrongArgumentException();
        }
    }

    private String parseItemsFile (String in) {
        return in;
    }

    private int parseEventsCount(String in, String def) throws WrongArgumentException {

        if (in == null)
            in = def;

        if (!StringUtils.isNumeric(in))
            throw new WrongArgumentException();

        return Integer.parseInt(in);
    }

    private String parseOutDir(String in){

        if (in == null)
            return ".";

        return in;
    }

    public InputData parse(String[] args)
            throws WrongArgumentException, ParseException {

        CommandLine cmd = new BasicParser().parse(newOptions(), args);

        Range<Integer> customerIds = parseRange(cmd.getOptionValue("customerIds"), "1:20");

        Range<Integer> itemsQuantity = parseRange(cmd.getOptionValue("itemsQuantity"), "1:5");

        Range<Integer> itemsCount = parseRange(cmd.getOptionValue("itemsCount"), "1:5");

        Range<ZonedDateTime> dateRange = parseDateRange(cmd.getOptionValue("dateRange"));

        int eventsCount = parseEventsCount(cmd.getOptionValue("eventsCount"), "100");

        String outDir = parseOutDir(cmd.getOptionValue("outDir"));

        String itemsFile = parseItemsFile(cmd.getOptionValue("itemsFile"));

        InputData result = new InputData(customerIds, dateRange, itemsFile,
                itemsCount, itemsQuantity, eventsCount, outDir);

        return result;
    }
}