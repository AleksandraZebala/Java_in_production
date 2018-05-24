import data.InputData;
import data.Transaction;
import exceptions.WrongArgumentException;
import exceptions.WrongFileException;
import module.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){
        try {
            String info = "Program started with arguments:";
            for(String arg : args)
                info += " " + arg;
            logger.info(info);
            InputData data = new CommandParser().parse(args);
            FileWrapper fileWrapper = new FileWrapper(data.getItemsFile());
            ArrayList<String> transactions = new Generator().generate(data, new ItemsParser().getItemsList(fileWrapper), new Randomizer());
            new OutputManager().saveToFiles(transactions, data.getOutDir());

            logger.info("Transactions generated succesfully");
        }
        catch(WrongArgumentException e){
            logger.error("Invalid arguments - please try again");
        }
        catch(WrongFileException e){
            logger.error("File not found - please try again");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
