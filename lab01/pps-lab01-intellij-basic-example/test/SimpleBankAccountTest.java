import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;


/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest extends AbstractBankAccountTest{

    @Override
    public BankAccount getBankAccount(final AccountHolder accountHolder) {
        return new SimpleBankAccount(accountHolder, 0);
    }

    @Override
    int getExpectedDeposit() {
        return 100;
    }

    @Override
    int getExpectedWrongDeposit() {
        return 100;
    }

    @Override
    int getExpectedWithdraw() {
        return 30;
    }

    @Override
    int getExpectedWrongWithdraw() {
        return 100;
    }
}
