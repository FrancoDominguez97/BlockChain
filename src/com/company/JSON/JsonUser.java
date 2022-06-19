package com.company.JSON;
import com.company.Interfaces.Json_User;
import com.company.Usuarios.User;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonUser extends JsonManager implements Json_User {


    public JsonUser(){
        
    }

    public static List<User> JsonToListUsers(String file) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                User[] userArray= objectMapper.readValue(new File(file),User[].class);
                List<User> userList = new ArrayList(Arrays.asList(userArray));
                return userList;
            } catch (IOException e){
                e.printStackTrace();
                return null;
            }
    }

    public static User searchUserByUserName(String file, String userName) {
        List<User>list = JsonToListUsers(file);
        for (User userFound : list){
            if (userFound.getUserName().equals(userName))
                return userFound;
        }
        return null;
    }


    public static String searchWalletIdUserByUserName(String file, String userName) {
        List<User> list = Json_User.JsonToListUsers(file);
        for (User userFound : list) {
            if(userFound.getUserName().equals(userName))
                return userFound.getWalletId();
        }
        return null;
    }


    public static User searchUserByIdWallet(String file, String walletId) {
        List<User> list = Json_User.JsonToListUsers(file);
        for (User userFound : list) {
            if (userFound.getWalletId().equals(walletId))
                return userFound;
        }
        return null;
    }
    public static User returnUserRand(){
        List<User> list = JsonToListUsers(JsonManager.JSON_USERS);
        Random ran = new Random();
        User userRand = list.get(ran.nextInt(list.size()));
        return userRand;
    }

    public static void printUsers(String file) {
        List<User> list = Json_User.JsonToListUsers(file);
        for (User user : list) {
            System.out.println(user.toString());
        }
    }

    public static boolean existEmail(List<User>list, String email){
        User user  = list.stream().filter(us -> us.getEmail().equals(email)).findFirst().orElse(null);
        return (user == null) ? true : false;
    }

    public static boolean existUserName(List<User>list, String userName){
        User user  = list.stream().filter(us -> us.getUserName().equals(userName)).findFirst().orElse(null);
        return (user == null) ? true : false;
    }
    public static HashMap<String, User> hashMapFromJson(String file) {
        List<User> users = JsonToListUsers(file);
        HashMap<String, User> loginInfo = new HashMap<>();
        for (User user : users) {
            loginInfo.put(user.getWalletId(), user);
        }
        return loginInfo;
    }
    public static void hashMapToJson(String file, HashMap<String, User> hashMap) {
        List<User> users = new ArrayList<>();
        hashMap.forEach((k, v) -> {
            users.add(v);
        });
        writeToJson(JsonManager.JSON_USERS, users);
    }
}
