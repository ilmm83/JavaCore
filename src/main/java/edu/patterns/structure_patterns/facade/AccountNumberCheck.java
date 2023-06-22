package edu.patterns.structure_patterns.facade;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountNumberCheck {

    private int accountNumber;


    public boolean isAccountNumberValid(int accountNumber) {
        return this.accountNumber == accountNumber;
    }
}
