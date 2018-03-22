import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;

public class Main {

    public static String output;

    public static void main(String[] args){

        try {
            Generator.generate(CommandParser.parse(args));
            output = "File generated";
        }
        catch(WrongArgumentException e){
            output = "Wrong arguments!";
        }
        catch(IOException e){
            output = "File not found!";
        }
        catch(Exception e){
            output =  "Wrong arguments!";
        }

        System.out.println(output);
    }
}
