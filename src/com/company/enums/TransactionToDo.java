package com.company.enums;

public enum TransactionToDo {
    SELECCIONE(0),
    NUEVA_TRANSFERENCIA(1),
    HISTORIAL_TRANSFERENCIAS(2);

    int value;
    TransactionToDo(int value) {
        this.value = value;
    }
}