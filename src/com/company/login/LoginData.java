package com.company.login;

import com.company.JsonManager;
import com.company.User;
import com.company.Wallet;

import java.util.HashMap;
import java.util.List;

public class LoginData {


    HashMap<String,User> loginInfo = new HashMap<String,User>();// falta agregar uuid parameter

    public LoginData() {
        JsonManager admin = new JsonManager();
        List<User> lista = admin.readJsonUser(JsonManager.JSON_USERS);
        for (User user: lista) {
            loginInfo.put(user.getWalletId(), user);
        }
    }

    public HashMap <String, User> getLoginInfo() {
        return loginInfo;
    }

}
