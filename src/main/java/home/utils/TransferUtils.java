package home.utils;

import home.model.Account;

import java.util.Objects;

/**
 * Transfer Utils
 * Util for transfer, withdraw and deposit
 */
public class TransferUtils {

    /**
     * Transfer Money
     * @param source
     * @param destination
     * @param amount
     */
    public void transferMoney(Account source,
                              Account destination,
                              long amount) {
        if (Objects.equals(source.getId(), destination.getId())) {
            throw new IllegalArgumentException("AccountDao id can't be the same.");
        }
        if (source.getBalance() < amount) {
            throw new IllegalArgumentException("Amount can't be greater than account balance.");
        }
        withdraw(source, amount);
        deposit(destination, amount);
    }

    /**
     * With draw some money from account
     * @param account
     * @param amount
     */
    private void withdraw(Account account, long amount) {
        account.setBalance(account.getBalance() - amount);
    }

    /**
     * Put some money into account
     * @param account
     * @param amount
     */
    private void deposit(Account account, long amount) {
        account.setBalance(account.getBalance() + amount);
    }

}
