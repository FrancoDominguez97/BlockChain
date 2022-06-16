package com.company.Transferencias;

import com.company.Usuarios.Coin;
import com.company.enums.Reason;
import com.company.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pending extends Transaction{
    private int validationCounter = 0;
    private String[] usersValidate = new String[4];

    public Pending(UUID sender, UUID receiver, LocalDateTime dateTime, Coin coin, Status status, Reason reason) {
        super(sender, receiver, dateTime, coin, status, reason);
        usersValidate[0]=sender.toString();
        usersValidate[1]=receiver.toString();
    }

    public int getValidationCounter() {
        return validationCounter;
    }
    public void setValidationCounter(int validationCounter) {
        this.validationCounter = validationCounter;
    }

}
