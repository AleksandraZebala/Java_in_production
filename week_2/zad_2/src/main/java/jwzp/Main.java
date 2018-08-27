package jwzp;

import jwzp.data.InputData;
import jwzp.data.Transaction;
import jwzp.exceptions.WrongArgumentException;
import jwzp.exceptions.WrongFileException;
import jwzp.module.*;
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
import java.util.Properties;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args){

        if (args.length == 1 && args[0].equals("docker")) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = new FileInputStream("/storage/generator.properties");
                properties.load(inputStream);
                args = new String[]{
                        "-customerIds", properties.getProperty("customerIds"),
                        "-dateRange", properties.getProperty("dateRange").replace("\"", ""),
                        "-itemsFile", "." + properties.getProperty("itemsFile"),
                        "-itemsCount", properties.getProperty("itemsCount"),
                        "-itemsQuantity", properties.getProperty("itemsQuantity"),
                        "-eventsCount", properties.getProperty("eventsCount"),
                        "-outDir", "." + properties.getProperty("outDir"),
                        "--format=" + properties.getProperty("format") };
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
