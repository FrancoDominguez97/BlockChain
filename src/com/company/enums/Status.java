package com.company.enums;

public enum Status {
    ACCEPTED(1),
    CANCELLED(2),
    REJECTED(3),
    PENDING(4);

    private int value;

    Status(int value) {
        this.value = value;
    }
}
