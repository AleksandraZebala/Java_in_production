import exceptions.WrongArgumentException;
import exceptions.WrongFileException;
import module.CommandParser;
import module.Generator;

public class Main {
    public static void main(String[] args){
        try {
            Generator.generateJSON(CommandParser.parse(args));
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
