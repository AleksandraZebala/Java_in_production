package jwzp.module;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileWrapper {

    private File file;

    public FileWrapper(String filePath){
        file = new File("src/main/resources/"+ filePath);
    }

    public boolean exists(){
        return file.exists();
    }

    public ArrayList<String> readAllLines() throws IOException {
       return (ArrayList)Files.readAllLines(file.toPath());
    }
}
