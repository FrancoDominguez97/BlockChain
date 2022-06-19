package com.company;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonTransfer;
import com.company.JSON.JsonUser;
import com.company.Transferencias.BlockTransfer;
import com.company.Transferencias.Transaction;
import com.company.Usuarios.Coin;
import com.company.Usuarios.User;
import com.company.Usuarios.Wallet;
import com.company.Visuals.LoginPage;
import com.company.enums.CoinName;
import com.company.enums.Status;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      // LoginPage loginPage = new LoginPage();
     /* String[] prueba = new String[2];
        prueba[0] = "HOLA";
        prueba[1] = "HOLA";
        prueba = Arrays.copyOf(new String[]{String.valueOf(prueba.length)},prueba.length+2);*/
        List<User> listaUsuarios = JsonUser.JsonToListUsers(JsonManager.JSON_USERS);


        BlockTransfer.send(listaUsuarios.get(0));
        //BlockTransfer.blockChain();




       /*
        Wallet wallet = new Wallet();
        wallet.setCoin(new Coin(CoinName.UTNCOIN,"UTN",100,"Moneda de la UTN Mar del plata"));
        User user = new User("Tincho","Martin", "Miranda", "18/04/1998","tincho@gmail.com", "tutuca123",wallet);
        User user1 = new User("fran","Franco", "Dominguez", "24/12/1997","fran@gmail.com", "tutuca123",wallet);
        User user2 = new User("tomi","Tomas", "Napolitano", "19/12/1997","tomi@gmail.com", "tutuca123",wallet);
        User user3 = new User("agus","Agustin", "Mineo", "29/03/2022","agus@gmail.com", "tutuca123",wallet);
        User user4 = new User("juan","Juan", "Dominguez", "30/04/1990","juan@gmail.com", "tutuca123",wallet);
        User user5 = new User("arian","Arian", "Provenzano", "17/07/1997","arian@gmail.com", "tutuca123",wallet);
        User user6 = new User("chaco","Cristian", "Fonteina", "24/07/1996","cristian@gmail.com", "tutuca123",wallet);
        User user7 = new User("yuli","Giuliana", "Dominguez", "11/01/1994","yuli@gmail.com", "tutuca123",wallet);
        User user8 = new User("pepe","Jose", "Perez", "24/02/1992","pepe@gmail.com", "tutuca123",wallet);
        User user9 = new User("beto","Roberto", "Gomez", "28/11/1989","beto@gmail.com", "tutuca123",wallet);
        User admin = new User("admin","admin", "istrador", "21/06/1986","admin@gmail.com", "tutuca123",wallet);

        admin.setAdmin(true);
        //System.out.println(user.equals(user));

        listaUsuarios.add(user);
        listaUsuarios.add(user1);
        listaUsuarios.add(user2);
        listaUsuarios.add(user3);
        listaUsuarios.add(user4);
        listaUsuarios.add(user5);
        listaUsuarios.add(user6);
        listaUsuarios.add(user7);
        listaUsuarios.add(user8);
        listaUsuarios.add(user9);
        listaUsuarios.add(admin);*/

        //System.out.println(JsonUser.existUserName(JsonUser.JsonToListUsers(JsonManager.JSON_USERS),"Arian"));


        JsonManager.writeToJson(JsonManager.JSON_USERS,listaUsuarios);

        /*
        List<User> listita = admin.readJsonUser(JSON_USERS);
        for (User users1:listita) {
            System.out.println(users1);
        }
        */
    }
}
