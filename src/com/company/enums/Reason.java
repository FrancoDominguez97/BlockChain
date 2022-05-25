package com.company.enums;

public enum Reason {
    OTHERS(1),
    FEE(2),
    RENT(3),
    SALARY(4);

    private int value;

    Reason(int value) {
        this.value = value;
    }
}
