import transaction.Transaction;
import transaction.TransactionRepository;
import transaction.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionRepositoryTest {
    private static final LocalDate TRANSACTION_DATE = LocalDate.of(2022, 2, 11);

    private TransactionRepository transactionRepository;

    @BeforeEach
    void init() {
        transactionRepository = new TransactionRepository();
    }

    @Test
    void verifyTransactionsHistoryWhenDepositTransactionCreation() {
        transactionRepository.createTransaction(TransactionRepositoryTest.TRANSACTION_DATE, BigDecimal.valueOf(100), TransactionType.DEPOSIT,
                BigDecimal.ZERO);
        List<Transaction> transactions = transactionRepository.findAllTransactions();
        assertEquals(1, transactions.size());
        assertEquals(transactions.get(0), depositTransaction(BigDecimal.valueOf(100)));
    }

    @Test
    void verifyTransactionsHistoryWhenWithdrawalTransactionCreation() {
        transactionRepository.createTransaction(TransactionRepositoryTest.TRANSACTION_DATE, BigDecimal.valueOf(100),
                TransactionType.DEPOSIT, BigDecimal.ZERO);
        List<Transaction> transactions = transactionRepository.findAllTransactions();
        assertEquals(1, transactions.size());
        assertEquals(transactions.get(0), withdrawalTransaction(BigDecimal.valueOf(100)));
    }

    private Transaction depositTransaction(BigDecimal amount) {
        return new Transaction(TransactionRepositoryTest.TRANSACTION_DATE, TransactionType.DEPOSIT, amount, BigDecimal.ZERO);
    }

    private Transaction withdrawalTransaction(BigDecimal amount) {
        return new Transaction(TransactionRepositoryTest.TRANSACTION_DATE, TransactionType.DEPOSIT, amount, BigDecimal.ZERO);
    }
}
