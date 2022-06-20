package com.company;

import com.company.JSON.JsonManager;
import com.company.Usuarios.User;
import com.company.Visuals.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static JsonManager jsonManager = new JsonManager();

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();

        User user = new User("Tincho","Martin", "Miranda", "18/04/1998","tincho@gmail.com", "tutuca123");
        User user1 = new User("fran","fran", "Miranda", "18/04/1998","fran@gmail.com", "tutuca123");
        User user2 = new User("tomi","tomi", "Miranda", "18/04/1998","tomi@gmail.com", "tutuca123");
        User admin = new User("admin","admin", "istrador", "21/06/1986","admin@gmail.com", "tutuca123");
        user.getWallet().initialize();
        user1.getWallet().initialize();
        user2.getWallet().initialize();
        admin.getWallet().initialize();

        admin.setAdmin(true);

        List<User> users = new ArrayList<>();

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(admin);


        //jsonManager.writeToJson(JsonManager.JSON_USERS,users);

        /*
        List<User> listita = admin.readJsonUser(JSON_USERS);
        for (User users1:listita) {
            System.out.println(users1);
        }
        */
    }
}
