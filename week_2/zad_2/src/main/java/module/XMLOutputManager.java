package module;

import data.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class XMLOutputManager implements OutputManager{

    private static final Logger logger = LogManager.getLogger(CommandParser.class.getName());

    public void saveToFiles(ArrayList<Transaction> transactions, String outDir) throws Exception {

        logger.info("Starting saving transactions to output folder...");

        File output = new File(outDir);

        if (!output.exists()) {
            output.mkdir();
            logger.info("Created output folder");
        }

        for(int i = 1; i <= transactions.size(); i++){

            JAXBContext jaxbContext = JAXBContext.newInstance(Transaction.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(transactions.get(i-1), new PrintWriter(output + "/output" + i + ".xml"));
        }

        logger.info("All "+transactions.size()+" transactions saved in "+outDir);
    }
}
