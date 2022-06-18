package com.company.enums;

public enum CoinName {
    UTNCOIN(1),
    BITCOIN(2),
    ETHEREUM(3),
    LUNA(4);

    int value;
    CoinName(int value) {
        this.value = value;
    }
}
