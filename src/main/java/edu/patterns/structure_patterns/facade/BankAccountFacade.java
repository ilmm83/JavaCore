package edu.patterns.structure_patterns.facade;

public class BankAccountFacade {

    private int securityCode;
    private int accountNumber;
    private FundsCheck fundsChecker;
    private WelcomeToBank bankWelcome;
    private SecurityCodeCheck securityCodeChecker;
    private AccountNumberCheck accountNumberChecker;


    public BankAccountFacade(int accountNumber, int securityCode) {
        this.accountNumber = accountNumber;
        this.securityCode = securityCode;

        fundsChecker = new FundsCheck();
        bankWelcome = new WelcomeToBank();
        securityCodeChecker = new SecurityCodeCheck(this.securityCode);
        accountNumberChecker = new AccountNumberCheck(this.accountNumber);
    }

    public void withdrawCash(double moneyToWithdrawal) {
        if (!isAllChecksSuccess()) return;

        fundsChecker.makeWithdrawal(moneyToWithdrawal);
    }

    public void depositCash(double moneyToDeposit) {
        if (!isAllChecksSuccess()) return;

        fundsChecker.makeDeposit(moneyToDeposit);
    }

    private boolean isAllChecksSuccess() {
        return securityCodeChecker.isCodeCorrect(securityCode) &&
                accountNumberChecker.isAccountNumberValid(accountNumber);
    }
}
