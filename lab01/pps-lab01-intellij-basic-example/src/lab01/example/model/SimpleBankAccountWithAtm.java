package lab01.example.model;

public class SimpleBankAccountWithAtm extends SimpleBankAccount{

    static final private int atmFee = 1;

    public SimpleBankAccountWithAtm(final AccountHolder holder, final double balance) {
        super(holder, balance);
    }

    @Override
    public void deposit(final int usrID, final double amount) {
        super.deposit(usrID, amount - atmFee);
    }

    @Override
    public void withdraw(final int usrID, final double amount) {
        super.withdraw(usrID,amount + atmFee);
    }
}
