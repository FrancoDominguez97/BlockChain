package com.company.login;

import java.util.HashMap;

public class LoginData {

    HashMap<String,String> loginInfo = new HashMap<String,String>();// falta agregar uuid parameter

    public LoginData() {
        loginInfo.put("Tincho","pass");// falta agregar el uuid parameter, hay que hacer un metodo de comparacion
        loginInfo.put("Fran","pass");
        loginInfo.put("Tomi","pass");
    }

    public HashMap <String, String> getLoginInfo() {
        return loginInfo;
    }

}
