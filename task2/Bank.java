import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Bank implements Serializable {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        Account account = new Account(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, account);
    }

    public Account findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void transferFunds(String fromAccount, String toAccount, double amount) throws Exception {
        Account source = findAccount(fromAccount);
        Account destination = findAccount(toAccount);

        if(source != null && destination != null) {
            source.withdraw(amount);
            destination.deposit(amount);
        } else {
            throw new Exception("One or both accounts not found");
        }
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }
}
