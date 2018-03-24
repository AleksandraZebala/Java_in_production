import data.InputData;
import data.JSONData;
import exceptions.WrongArgumentException;
import exceptions.WrongFileException;
import module.CommandParser;
import module.Generator;

public class Main {
    public static void main(String[] args){
        try {
            InputData input = CommandParser.parse(args);
            Generator.generateToFile(input);
            System.out.println("File generated!");
        }
        catch(WrongArgumentException e){
            System.out.println("Wrong arguments!");
        }
        catch(WrongFileException e){
            System.out.println("File not found!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
