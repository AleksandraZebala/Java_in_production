import data.InputData;
import data.JSONData;
import exceptions.WrongArgumentException;
import exceptions.WrongFileException;
import module.*;

import java.io.OutputStream;

public class Main {
    public static void main(String[] args){
        try {

            InputData data = CommandParser.parse(args);
            FileWrapper fileWrapper = new FileWrapper(data.itemsFile);
            String result = Generator.generate(data, ItemsParser.getItemsList(fileWrapper), new Randomizer());
            OutputManager.saveToFile(result, data.outDir);

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
