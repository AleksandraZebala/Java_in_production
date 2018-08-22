package module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import data.Transaction;

public class JSONOutputManager implements OutputManager{

    private static final Logger logger = LogManager.getLogger(CommandParser.class.getName());

    public void saveToFiles(ArrayList<Transaction> transactions, String outDir) throws Exception {

        logger.info("Starting saving transactions to output folder...");

        Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();

        File output = new File(outDir);

        if (!output.exists()) {
            output.mkdir();
            logger.info("Created output folder");
        }

        for(int i = 1; i <= transactions.size(); i++){

            PrintWriter out = new PrintWriter(output + "/output" + i + ".json");
            out.println(gsonPretty.toJson(transactions.get(i-1)));
            out.close();
        }

        logger.info("All "+transactions.size()+" transactions saved in "+outDir);
    }
}
