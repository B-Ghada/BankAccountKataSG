package main.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private final List<Transaction> transactionHistory = new ArrayList<>();

    public Transaction createTransaction(LocalDate date, BigDecimal amount, TransactionType type, BigDecimal postTransactionBalance) {
        Transaction transaction = new Transaction(date, type, amount, postTransactionBalance);
        transactionHistory.add(transaction);
        return transaction;
    }

    public List<Transaction> findAllTransactions() {
        return transactionHistory;
    }
}
