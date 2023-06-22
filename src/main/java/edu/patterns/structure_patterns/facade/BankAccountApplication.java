package edu.patterns.structure_patterns.facade;

public class BankAccountApplication {
    public static void main(String[] args) {
        BankAccountFacade accessingBank = new BankAccountFacade(1234, 1234);

        accessingBank.withdrawCash(50.00);
        accessingBank.withdrawCash(900.00);
        accessingBank.depositCash(200.00);
        accessingBank.withdrawCash(900.00);
    }
}
