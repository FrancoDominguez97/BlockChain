package com.company.Transferencias;

import com.company.enums.Reason;
import com.company.enums.Status;
import com.company.Usuarios.Coin;

import java.time.LocalDateTime;
import java.util.UUID;

public class Accepted extends Transaction{
    public String status = "ACCEPTED";

    public Accepted(UUID sender, UUID receiver, LocalDateTime dateTime, Coin coin, Status status, Reason reason) {
        super(sender, receiver, dateTime, coin, status, reason);
    }

    public Accepted(){

    }

}
