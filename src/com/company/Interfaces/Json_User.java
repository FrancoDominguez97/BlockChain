package com.company.Interfaces;

import com.company.Usuarios.User;

import java.util.HashMap;
import java.util.List;

public interface Json_User {

    public static List<User> JsonToListUsers(String file) { return null; }

    public static User searchUserByUserName(String file, String userName) { return null; }

    public static String searchWalletIdUserByUserName(String file, String userName) { return null; }

    public static User searchUserByIdWallet(String file, String walletId0) { return null; }

    public static void printUsers(String file) { }

    public static boolean existEmail(List<User>list , String email) { return false; }

    public static boolean existUserName(List<User>list , String userName) { return false; }

    public static void hashMapToJson(String file, HashMap<String, User> hashMap) { }

    public static HashMap<String, User> hashMapFromJson(String file) { return null; }
}
