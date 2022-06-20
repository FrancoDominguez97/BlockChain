package com.company.enums;

public enum TransactionToDo {
    SELECCIONE(0),
    NUEVA_TRANSFERENCIA(1),
    HISTORIAL_TRANSFERENCIAS(2),
    VER_BLOCKCHAIN(3),
    PENDING_TRANSFERS(4);

    int value;
    TransactionToDo(int value) {
        this.value = value;
    }
}