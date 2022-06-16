package com.company;

import com.company.login.LoginData;
import com.company.login.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static JsonManager admin = new JsonManager();

    public static void main(String[] args) {
        LoginData loginData = new LoginData();
        LoginPage loginPage = new LoginPage(loginData.getLoginInfo());

        User user = new User("Tincho","Martin", "Miranda", "18/04/1998","tincho@gmail.com", "tutuca123");
        User user1 = new User("fran","fran", "Miranda", "18/04/1998","fran@gmail.com", "tutuca123");
        User user2 = new User("tomi","tomi", "Miranda", "18/04/1998","tomi@gmail.com", "tutuca123");

        List<User> users = new ArrayList<>();

        users.add(user);
        users.add(user1);
        users.add(user2);

        // admin.writeToJson(JSON_USERS,users);

        /*
        List<User> listita = admin.readJsonUser(JSON_USERS);
        for (User users1:listita) {
            System.out.println(users1);
        }
        */
    }
}
