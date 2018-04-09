import com.external.PaymentsService;
import com.internal.DiscountCalculator;

import org.apache.commons.cli.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        DiscountCalculator discountCalculator = new DiscountCalculator();
        PaymentsService paymentsService = new PaymentsService();

        Logger logger = LoggerFactory.getLogger(Main.class);

        logger.info("Program started with arguments: "+ Arrays.toString(args));

        CommandLine cmd;

        try {

            Options options = new Options();
            options.addRequiredOption("price", null, true, "");
            options.addRequiredOption("age", null, true, "");
            options.addRequiredOption("clientId", null, true, "");
            options.addRequiredOption("companyId", null, true, "");

            CommandLineParser commandLineParser = new DefaultParser();
            cmd = commandLineParser.parse(options, args);
        }

        catch (MissingOptionException e) {
            logger.error("Missed arguments: " + e.getMissingOptions());
            return;
        }

        catch (Exception e){
            logger.error(e.getMessage());
            return;
        }

        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(cmd.getOptionValue("price")));
        Integer age = Integer.parseInt(cmd.getOptionValue("age"));
        Integer clientId = Integer.parseInt(cmd.getOptionValue("clientId"));
        Integer companyId = Integer.parseInt(cmd.getOptionValue("companyId"));

        BigDecimal result = price.subtract(discountCalculator.calculateDiscount(price, age));

        logger.info("Making payment transaction of " + result + " dollars from " + clientId + " to " + companyId);

        if (paymentsService.makePayment(clientId, companyId, result)) {
            logger.info("Transaction successfull!");
        }

        else {
            logger.info("Transaction denied!");
        }
    }
}
