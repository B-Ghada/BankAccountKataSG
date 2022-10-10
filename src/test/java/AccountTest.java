
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transaction.Transaction;
import transaction.TransactionRepository;
import transaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountTest {
    private Account account;
    TransactionRepository transactionRepository = mock(TransactionRepository.class);
    Transaction validWithdrawalTransaction = new Transaction(LocalDate.now(),
            TransactionType.WITHDRAW, BigDecimal.valueOf(200), BigDecimal.valueOf(200));

    Transaction validDepositTransaction = new Transaction(LocalDate.now(),
            TransactionType.DEPOSIT, BigDecimal.valueOf(200), BigDecimal.valueOf(200));

    @BeforeEach
    void init() {
        account = new Account(transactionRepository);
    }


    @Test
    void should_return_balance_minus_200_when_withdrawal_of_amount_200() {
        //given
        BigDecimal amount = new BigDecimal("200");
        TransactionType type = TransactionType.WITHDRAW;
        //when
        when(transactionRepository.createTransaction(any(), any(), any(), any()))
                .thenReturn(validWithdrawalTransaction);
        account.createTransaction(amount, type);
        //should
        assertEquals(BigDecimal.valueOf(-200), account.getAccountBalance());
    }

    @Test
    void should_return_balance_200_when_deposit_of_amount_200() {
        //given
        BigDecimal amount = new BigDecimal("200");
        TransactionType type = TransactionType.DEPOSIT;
        //when
        when(transactionRepository.createTransaction(any(), any(), any(), any()))
                .thenReturn(validDepositTransaction);
        account.createTransaction(amount, type);
        //should
        assertEquals(BigDecimal.valueOf(200), account.getAccountBalance());
    }


    @Test
    void number_of_withdraw_created_should_be_1_when_one_withdrawal_operationn_is_done() {
        account.createTransaction(BigDecimal.valueOf(100), TransactionType.WITHDRAW);
        verify(transactionRepository, times(1)).createTransaction(LocalDate.now(),
                BigDecimal.valueOf(-100), TransactionType.WITHDRAW, BigDecimal.valueOf(-100));
    }

    @Test
    void number_of_deposit_created_should_be_1_when_one_deposit_operation_is_done() {
        account.createTransaction(BigDecimal.valueOf(100), TransactionType.DEPOSIT);
        verify(transactionRepository, times(1)).createTransaction(LocalDate.now(),
                BigDecimal.valueOf(100), TransactionType.DEPOSIT, BigDecimal.valueOf(100));
    }

    @Test
    public void should_return_balance_200_when_withdrawal_amount_50_and_deposit_250() {
        //when
        account.createTransaction(BigDecimal.valueOf(50), TransactionType.WITHDRAW);
        account.createTransaction(BigDecimal.valueOf(250), TransactionType.DEPOSIT);
        //should return
        assertEquals(BigDecimal.valueOf(200), account.getAccountBalance());
    }

}
