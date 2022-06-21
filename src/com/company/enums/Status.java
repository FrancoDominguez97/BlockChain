package com.company.enums;

public enum Status {
    ACCEPTED(1),
    PENDING(4);

    private int value;

    Status(int value) {
        this.value = value;
    }
}
