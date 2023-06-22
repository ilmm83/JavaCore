package edu.patterns.structure_patterns.facade;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityCodeCheck {

    private int securityCode;


    public boolean isCodeCorrect(int securityCode) {
        return this.securityCode == securityCode;
    }
}
