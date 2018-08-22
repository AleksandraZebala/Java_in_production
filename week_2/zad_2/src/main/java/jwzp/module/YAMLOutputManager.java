package jwzp.module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import jwzp.data.Transaction;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

@Service("yaml")
public class YAMLOutputManager implements OutputManager{

    private static final Logger logger = LogManager.getLogger(CommandParser.class.getName());

    public void saveToFiles(ArrayList<Transaction> transactions, String outDir) throws Exception {

        logger.info("Starting saving transactions to output folder...");

        File output = new File(outDir);

        if (!output.exists()) {
            output.mkdir();
            logger.info("Created output folder");
        }
        Representer representer = new Representer();
        representer.addClassTag(Transaction.class, new Tag("!transaction"));
        Yaml yaml = new Yaml(representer, new DumperOptions());
        for(int i = 1; i <= transactions.size(); i++){


            yaml.dump(transactions.get(i-1), new PrintWriter(output + "/output" + i + ".yaml"));

        }

        logger.info("All "+transactions.size()+" transactions saved in "+outDir);
    }
}

