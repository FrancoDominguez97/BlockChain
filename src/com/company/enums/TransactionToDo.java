package com.company.enums;

public enum TransactionToDo {
    SELECCIONE(1),
    NUEVA_TRANSFERENCIA(2),
    HISTORIAL_TRANSFERENCIAS(3);

    int value;
    TransactionToDo(int value) {
        this.value = value;
    }
}