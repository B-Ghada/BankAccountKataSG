package main;


import main.transaction.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Account {

    private static final String STATEMENT_HEADER = "DATE\t  |\t\t  Amount|   Balance|\n";
    public final TransactionRepository transactionRepository;


    private BigDecimal accountBalance = BigDecimal.ZERO;

    public Account(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(BigDecimal amount, TransactionType type) {
        if (type == TransactionType.WITHDRAW) {
            amount = amount.negate();
        }
        accountBalance = accountBalance.add(amount);
        transactionRepository.createTransaction(LocalDate.now(), amount, type, accountBalance);
    }

    public void printTransactionsHistory(List<Transaction> transactions) {
        System.out.print(STATEMENT_HEADER);
        transactions.stream().map(transaction -> formatTransaction(transaction)).forEachOrdered(System.out::print);
    }


    public String formatTransaction(Transaction transaction) {
        return transaction.getTransactionDate() + String.format("|%13.2f|", transaction.getTransactionAmount())
                + String.format("%10.2f|", transaction.getPostTransactionBalance()) + "\n";
    }

    public void printStatement() {
        List<Transaction> transactions = transactionRepository.findAllTransactions();
        printTransactionsHistory(transactions);
    }


    public BigDecimal getAccountBalance() {
        return accountBalance;
    }
}
