package com.company.Transferencias;

import com.company.Usuarios.Coin;
import com.company.enums.Reason;
import com.company.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;


public class Rejected extends Transaction {
    private String status = "RECHAZADO";

    public Rejected(UUID sender, UUID receiver, LocalDateTime dateTime, Coin coin, Status status, Reason reason) {
        super(sender, receiver, dateTime, coin, status, reason);
    }

}

