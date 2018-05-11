import data.InputData;
import exceptions.WrongArgumentException;
import exceptions.WrongFileException;
import module.*;

public class Main {
    public static void main(String[] args){
        try {

            InputData data = new CommandParser().parse(args);
            FileWrapper fileWrapper = new FileWrapper(data.getItemsFile());
            String result = new Generator().generate(data, new ItemsParser().getItemsList(fileWrapper), new Randomizer());
            new OutputManager().saveToFile(result, data.getOutDir());

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
