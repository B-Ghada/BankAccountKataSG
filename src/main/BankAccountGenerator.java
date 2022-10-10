package main;

import main.transaction.TransactionRepository;
import main.transaction.TransactionType;

import static java.math.BigDecimal.valueOf;

public class BankAccountGenerator {
    public static void main(String[] args) {
        TransactionRepository transactionRepository = new TransactionRepository();
        Account account = new Account(transactionRepository);

        account.createTransaction(valueOf(100), TransactionType.DEPOSIT);
        account.createTransaction(valueOf(1200), TransactionType.DEPOSIT);
        account.createTransaction(valueOf(50), TransactionType.WITHDRAW);

        account.printStatement();
        System.out.println("\n#######");
        account.printStatement();
    }
}