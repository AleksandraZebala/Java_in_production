package jwzp;

import jwzp.broker.JmsQueueProducer;
import jwzp.broker.JmsTopicProducer;
import jwzp.data.InputData;
import jwzp.data.Transaction;
import jwzp.exceptions.WrongArgumentException;
import jwzp.exceptions.WrongFileException;
import jwzp.module.*;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args){

        ApplicationContext applicationContext = getApplicationContext(args);
        try {
            String info = "Program started with arguments:";
            for(String arg : args)
                info += " " + arg;
            logger.info(info);
            CommandParser commandParser = (CommandParser) applicationContext.getBean("commandParser");
            Generator generator = (Generator) applicationContext.getBean("generator");

            args = Arrays.stream(args).filter(s -> !s.contains("--format")).toArray(String[]::new);

            InputData data = commandParser.parse(args);
            FileWrapper fileWrapper = new FileWrapper(data.getItemsFile());
            ArrayList<Transaction> transactions = generator.generate(data, fileWrapper);

            String saverFormat = applicationContext.getEnvironment().getProperty("format");
            if(saverFormat == null)
                saverFormat = "json";

            OutputManager outputManager = (OutputManager) applicationContext.getBean(saverFormat);

            outputManager.saveToFiles(transactions, data.getOutDir());

            logger.info("Transactions generated succesfully");

            String broker = data.getBroker();
            logger.info("Broker url: " + broker);
            if(broker != null){
                String queue = data.getQueue();
                String topic = data.getTopic();
                if(queue != null){
                    logger.info("Sending transactions to the queue named: " + queue);
                    JmsTopicProducer jmsTopicProducer = new JmsTopicProducer(broker, topic, transactions);
                    jmsTopicProducer.sendTransactionsAsMessages();
                }
                if(topic != null){
                    logger.info("Sending transactions to the topic named: " + topic);
                    JmsQueueProducer jmsQueueProducer = new JmsQueueProducer(broker, queue, transactions);
                    jmsQueueProducer.sendTransactionsAsMessages();
                }
            }
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

    private static ApplicationContext getApplicationContext(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        PropertySource theSource = new SimpleCommandLinePropertySource(args);
        ctx.getEnvironment().getPropertySources().addFirst(theSource);
        ctx.scan("jwzp");
        ctx.refresh();
        return ctx;
    }
}
