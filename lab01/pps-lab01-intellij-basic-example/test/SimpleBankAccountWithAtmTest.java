import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountWithAtm;

public class SimpleBankAccountWithAtmTest extends AbstractBankAccountTest {

    @Override
    public BankAccount getBankAccount(final AccountHolder accountHolder) {
        return new SimpleBankAccountWithAtm(accountHolder, 0);
    }

    @Override
    int getExpectedDeposit() {
        return 99;
    }

    @Override
    int getExpectedWrongDeposit() {
        return 99;
    }

    @Override
    int getExpectedWithdraw() {
        return 28;
    }

    @Override
    int getExpectedWrongWithdraw() {
        return 99;
    }
}
