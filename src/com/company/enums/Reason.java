package com.company.enums;

public enum Reason {
    OTHERS(0),
    FEE(1),
    RENT(2),
    SALARY(3);

    private int value;

    Reason(int value) {
        this.value = value;
    }
}
