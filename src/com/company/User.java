package com.company;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID walletId;
    private String userName;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;

    private List<Transaction> transactionList;
    private List<Wallet> walletList;

}
