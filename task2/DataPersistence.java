import java.io.*;
import java.util.Map;

public class DataPersistence {
    public static void saveAccounts(Map<String, Account> accounts, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(accounts);
        }
    }

    public static Map<String, Account> loadAccounts(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Map<String, Account>) ois.readObject();
        }
    }
}
