package transaction;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Transaction {
    private final String transactionDate;
    private final TransactionType transactionType;
    private final BigDecimal transactionAmount;
    private final BigDecimal postTransactionBalance;


    public String getTransactionDate() {
        return transactionDate;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public BigDecimal getPostTransactionBalance() {
        return postTransactionBalance;
    }

    public Transaction(LocalDate transactionDate, TransactionType type, BigDecimal amount, BigDecimal postTransactionBalance) {
        this.transactionDate = formatDate(transactionDate);
        this.transactionType = type;
        this.transactionAmount = amount;
        this.postTransactionBalance = postTransactionBalance;


    }

    public String formatDate(LocalDate date) {
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateFormatter.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionDate, that.transactionDate)
                && Objects.equals(transactionType, that.transactionType)
                && Objects.equals(transactionAmount, that.transactionAmount)
                && Objects.equals(postTransactionBalance, that.postTransactionBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, transactionType, transactionAmount, postTransactionBalance);
    }


}
