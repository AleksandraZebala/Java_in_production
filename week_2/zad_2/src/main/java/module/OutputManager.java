package module;

import java.util.ArrayList;

import data.Transaction;

/**
 * Created by Olusia on 2018-08-22.
 */
public interface OutputManager {
    void saveToFiles(ArrayList<Transaction> transactions, String outDir) throws Exception;
}
