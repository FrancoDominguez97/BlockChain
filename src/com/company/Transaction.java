package com.company;

import com.company.enums.Reason;
import com.company.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private LocalDateTime dateTime;
    private Coin coin;
    private double amount;
    private int validationCounter;
    private Status status;
    private Reason reason;

}
