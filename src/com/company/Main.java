package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static final String JSON_BLOCKCHAIN = "blockchain.json"; //Archivo de transacciones ya validadas (el blockchain en sí)
    public static final String JSON_USERS = "users.json"; //Archivo con registros de todos los usuarios
    public static final String JSON_PENDING_TRANSACTIONS = "pending_transactions.json"; //Archivo con registros de transacciones pendientes (todavía no fueron validadas por completo)
    public static final String JSON_CANCELLED_TRANSACTIONS = "cancelled_transactions.json"; //Archivo con registros de transacciones canceladas (se cancelaron por el emisor antes de ser validadas por completo)

    public static JsonManager admin = new JsonManager();

    public static void main(String[] args) {

        User user = new User("Tincho","Martin", "Miranda", "18/04/1998","tincho@gmail.com", "tutuca123");
        User user1 = new User("fran","fran", "Miranda", "18/04/1998","fran@gmail.com", "tutuca123");
        User user2 = new User("tomi","tomi", "Miranda", "18/04/1998","tomi@gmail.com", "tutuca123");

        List<User> users = new ArrayList<>();

        users.add(user);
        users.add(user1);
        users.add(user2);


        //admin.writeToJson(JSON_USERS,users);


        List<User> listita = admin.readJsonUser(JSON_USERS);
        for (User users1:listita) {
            System.out.println(users1);
        }



    }

}
