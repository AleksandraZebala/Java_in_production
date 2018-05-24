package module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class OutputManager {

    private static final Logger logger = LogManager.getLogger(CommandParser.class.getName());

    public void saveToFiles(ArrayList<String> transactions, String outDir) throws Exception {

        logger.info("Starting saving transactions to output folder...");

        File output = new File(outDir);

        if (!output.exists()) {
            output.mkdir();
            logger.info("Created output folder");
        }

        for(int i = 1; i <= transactions.size(); i++){

            PrintWriter out = new PrintWriter(output + "/output" + i + ".json");
            out.println(transactions.get(i-1));
            out.close();
        }

        logger.info("All "+transactions.size()+" transactions saved in "+outDir);
    }
}
