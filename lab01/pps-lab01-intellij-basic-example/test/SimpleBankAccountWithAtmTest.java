import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBankAccountWithAtmTest extends AbstractBankAccountTest {

    @Override
    public BankAccount getBankAccount(final AccountHolder accountHolder) {
        return new SimpleBankAccountWithAtm(accountHolder, 0);
    }

    @Test
    void testDeposit() {
        getBankAccount().deposit(getAccountHolder().getId(), 100);
        assertEquals(99, getBankAccount().getBalance());
    }

    @Test
    void testWrongDeposit() {
        getBankAccount().deposit(getAccountHolder().getId(), 100);
        getBankAccount().deposit(2, 50);
        assertEquals(99, getBankAccount().getBalance());
    }

    @Test
    void testWithdraw() {
        getBankAccount().deposit(getAccountHolder().getId(), 100);
        getBankAccount().withdraw(getAccountHolder().getId(), 70);
        assertEquals(28, getBankAccount().getBalance());
    }

    @Test
    void testWrongWithdraw() {
        getBankAccount().deposit(getAccountHolder().getId(), 100);
        getBankAccount().withdraw(2, 70);
        assertEquals(99, getBankAccount().getBalance());
    }
}
