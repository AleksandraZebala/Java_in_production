package jwzp.module;

import jwzp.data.InputData;
import jwzp.data.Range;
import jwzp.exceptions.WrongArgumentException;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public final class CommandParser {

    private static final Logger logger = LogManager.getLogger(CommandParser.class.getName());

    private Options newOptions() {
        Options options = new Options();
        options.addOption("customerIds", true, "customerIds");
        options.addOption("dateRange", true, "dateRange");
        options.addOption("itemsFile", true, "itemsFile");
        options.addOption("itemsCount", true, "itemsCount");
        options.addOption("itemsQuantity", true, "itemsQuantity");
        options.addOption("eventsCount", true, "eventsCount");
        options.addOption("outDir", true, "outDir");
        options.addOption("broker", true, "broker");
        options.addOption("queue", true, "queue");
        options.addOption("topic", true, "topic");

        return options;
    }

    private Range<Integer> parseRange(String in, String default_values)
            throws WrongArgumentException {

        if(in == null) {
            logger.info("Parsing default range value: " + default_values);
            in = default_values;
        }

        if (!in.contains(":")){
            logger.error("Invalid range argument value: Range must be splited by character \":\"");
            throw new WrongArgumentException();
        }

        String[] stringValues = in.split(":");

        if (stringValues.length != 2){
            logger.error("Invalid range argument value: Range must have two arguments");
            throw new WrongArgumentException();
        }

        if (!StringUtils.isNumeric(stringValues[0]) || !StringUtils.isNumeric(stringValues[1])) {
            logger.error("Invalid range argument value: Range arguments must be numeric");
            throw new WrongArgumentException();
        }

        int[] intValues = {Integer.parseInt(stringValues[0]), Integer.parseInt(stringValues[1])};

        if(intValues[0] > intValues[1]) {
            logger.error("Invalid range argument value: First range argument must be smaller");
            throw new WrongArgumentException();
        }

        logger.info("Parsed range: " + intValues[0] + ":" + intValues[1]);
        return new Range(intValues[0], intValues[1]);
    }

    private Range<ZonedDateTime> parseDateRange(String in) throws WrongArgumentException {

        if (in == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            in = LocalDateTime.now().format(formatter);

            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            ZonedDateTime from = ZonedDateTime.parse(in + "T00:00:00.000-0100", formatter2);
            ZonedDateTime to = ZonedDateTime.parse(in + "T23:59:59.999-0100", formatter2);
            logger.info("Parsing default dateRange argument value: " + from.toString() + "-" + to.toString());
            logger.info("Parsed dateRange argument value: " + from.toString() + "-" + to.toString());
            return new Range(from, to);
        }

        if (in.length() != 57) {
            logger.error("Invalid dateRange argument value");
            throw new WrongArgumentException();
        }

        String from = in.substring(0, 28);
        String to = in.substring(29, 57);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        try {
            Range result = new Range(ZonedDateTime.parse(from, formatter), ZonedDateTime.parse(to, formatter));
            logger.info("Parsed dateRange argument value: " + result.getFrom().toString() + "-" + result.getTo().toString());
            return result;
        } catch(DateTimeParseException e){
            logger.error("Invalid dateRange argument value");
            throw new WrongArgumentException();
        }
    }

    private String parseItemsFile (String in) throws WrongArgumentException{

        if (in == null){
            logger.error("itemsFile argument is required");
            throw new WrongArgumentException();
        }

        logger.info("Parsed itemsFile argument: " + in);
        return in;
    }

    private int parseEventsCount(String in, String def) throws WrongArgumentException {

        if (in == null) {
            logger.info("Parsing default eventsCount arguments: " + def);
            in = def;
        }

        if (!StringUtils.isNumeric(in)){
            logger.error("eventsCount argument must be numeric");
            throw new WrongArgumentException();
        }

        logger.info("Parsed eventsCount arguments: " + in);
        return Integer.parseInt(in);
    }

    private String parseOutDir(String in){

        if (in == null) {
            logger.info("Parsing default outDir argument: current folder");
            return ".";
        }

        logger.info("Parsed outDir argument: "+ in);
        return in;
    }

    public InputData parse(String[] args)
            throws WrongArgumentException, ParseException {

        CommandLine cmd = new BasicParser().parse(newOptions(), args);

        Range<Integer> customerIds;
        Range<Integer> itemsQuantity;
        Range<Integer> itemsCount;
        Range<ZonedDateTime> dateRange;
        int eventsCount;
        String outDir;
        String itemsFile;

        try
        {
            logger.info("Parsing customerIds argument...");
            customerIds = parseRange(cmd.getOptionValue("customerIds"), "1:20");
        }

        catch(WrongArgumentException e){
            logger.error("Invalid customerIds argument value");
            throw e;
        }

        try {
            logger.info("Parsing itemsQuantity argument...");
            itemsQuantity = parseRange(cmd.getOptionValue("itemsQuantity"), "1:5");
        }
        catch(WrongArgumentException e){
            logger.error("Invalid itemsQuantity value");
            throw e;
        }

        try {
            logger.info("Parsing itemsCount argument...");
            itemsCount = parseRange(cmd.getOptionValue("itemsCount"), "1:5");
        }
        catch(WrongArgumentException e){
            logger.error("Invalid itemsCount argument value");
            throw e;
        }

        try {
            logger.info("Parsing dateRange argument...");
            dateRange = parseDateRange(cmd.getOptionValue("dateRange"));
        }
        catch(WrongArgumentException e){
            logger.error("Invalid dateRange argument...");
            throw e;
        }

        try {
            logger.info("Parsing eventsCount argument...");
            eventsCount = parseEventsCount(cmd.getOptionValue("eventsCount"), "100");
        }
        catch(WrongArgumentException e){
            logger.error("Invalid eventsCount argument value");
            throw e;
        }

        logger.info("Parsing outDir argument...");
        outDir = parseOutDir(cmd.getOptionValue("outDir"));

        logger.info("Parsing itemsFile argument...");
        itemsFile = parseItemsFile(cmd.getOptionValue("itemsFile"));

        InputData result = new InputData(customerIds, dateRange, itemsFile,
                itemsCount, itemsQuantity, eventsCount, outDir,
                cmd.getOptionValue("broker"), cmd.getOptionValue("queue"), cmd.getOptionValue("topic"));

        return result;
    }
}