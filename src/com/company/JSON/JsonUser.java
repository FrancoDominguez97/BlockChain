package com.company.JSON;

import com.company.Usuarios.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JsonUser extends JsonManager {

    // metodo para leer e imprimir un json de objetos User.
    public static void printJsonUser(String file) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User[] userArray = objectMapper.readValue(new File(file), User[].class);
            List<User> personaList = new ArrayList(Arrays.asList(userArray));

            for (User u : personaList) {
                System.out.println(u);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> readJsonUser(String file) //No se puede generalizar por ser necesario obtener .class
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            User[] userArray = objectMapper.readValue(new File(file), User[].class);
            //System.out.println(Arrays.toString(userArray));
            List<User> userList = new ArrayList(Arrays.asList(userArray));
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // se puede generalizar? ----------------------------------------------------------------------------------------------------
    public static HashMap<String, User> hashMapFromJson(String file) {
        List<User> users = readJsonUser(file);
        HashMap<String, User> loginInfo = new HashMap<>();
        if (!users.isEmpty()) {
            for (User user : users) {
                loginInfo.put(user.getWalletId(), user);
            }
        }
        return loginInfo;
    }

    // se puede generalizar? ----------------------------------------------------------------------------------------------------
    public static void hashMapToJson(String file, HashMap<String, User> hashMap) {
        List<User> users = new ArrayList<>();
        hashMap.forEach((k, v) -> {
            users.add(v);
        });
        writeToJson(file, users);
    }

    // searchUser implementando java 8
    public static User searchUserByIdWallet(String file, String walletId) {
        List<User> list = readJsonUser(file);
        User user = list.stream().filter(us -> us.getWalletId().equals(walletId)).findFirst().orElse(null);
        return user;
    }
    public static User searchUserByUserName(String file, String userName) {
        List<User> list = readJsonUser(file);
        User user = list.stream().filter(us -> us.getUserName().equals(userName)).findFirst().orElse(null);
        return user;
    }

    public static void updateUser (User user){
        List<User> usersToUpdate = readJsonUser(JsonManager.JSON_USERS);
        // funciona igual que un foreach
        List<User> usersUpdated = usersToUpdate.stream()
                .map(us -> {
                    if (us.getWalletId().equals(user.getWalletId()))
                        us = user;
                    return us;
                })
                .collect(Collectors.toList());

        writeToJson(JSON_USERS,usersUpdated);
    }
}
