package edu.patterns.structure_patterns.facade;

import lombok.Getter;

@Getter
public class FundsCheck {

    private double cashInAccount = 1000.0;


    public void decreaseCashInAccount(double cash) {
        cashInAccount -= cash;
    }

    public void increaseCashInAccount(double cash) {
        cashInAccount += cash;
    }

    public boolean haveEnoughMoney(double moneyToWithdrawal) {
        if (cashInAccount < moneyToWithdrawal) {
            System.out.println("You don't have enough money! Current balance is " + cashInAccount);

            return false;
        }
        else return true;
    }

    public void makeDeposit(double money) {
        increaseCashInAccount(money);

        System.out.println("Deposit success! Current balance is " + cashInAccount);
    }

    public void makeWithdrawal(double money) {
        if (!haveEnoughMoney(money)) return;

        decreaseCashInAccount(money);
        System.out.println("Withdrawal success! Current balance is " + cashInAccount);
    }
}
