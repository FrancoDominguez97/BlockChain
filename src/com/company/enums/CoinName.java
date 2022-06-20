package com.company.enums;

public enum CoinName {
    UTNCOIN(0),
    BITCOIN(1),
    ETHEREUM(2),
    LUNA(3);

    int value;
    CoinName(int value) {
        this.value = value;
    }
}
