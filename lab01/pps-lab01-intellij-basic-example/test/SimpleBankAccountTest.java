import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest extends AbstractBankAccountTest{

    @Override
    public BankAccount getBankAccount(final AccountHolder accountHolder) {
        return new SimpleBankAccount(accountHolder, 0);
    }
}
