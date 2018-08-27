package jwzp.module;

import java.util.ArrayList;
import jwzp.data.Transaction;

public interface OutputManager {
    void saveToFiles(ArrayList<Transaction> transactions, String outDir) throws Exception;
}
