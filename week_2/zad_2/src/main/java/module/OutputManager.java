package module;

import java.io.File;
import java.io.PrintWriter;

public class OutputManager {

    public static void saveToFile(String data, String outDir) throws Exception {

        File output = new File(outDir);

        if (!output.exists())
            output.mkdir();

        PrintWriter out = new PrintWriter(output + "/output.json");
        out.println(data);
        out.close();
    }
}
