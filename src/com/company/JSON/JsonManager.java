package com.company.JSON;

import com.company.Transferencias.Transaction;
import com.company.Usuarios.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JsonManager {

    public static final String JSON_BLOCKCHAIN = "blockchain.json"; //Archivo de transacciones ya validadas (el blockchain en sí)
    public static final String JSON_USERS = "users.json"; //Archivo con registros de todos los usuarios
    public static final String JSON_PENDING_TRANSACTIONS = "pending_transactions.json"; //Archivo con registros de transacciones pendientes (todavía no fueron validadas por completo)
    public static final String JSON_CANCELLED_TRANSACTIONS = "cancelled_transactions.json";
    // ---- Agrego esto por acá, son los métodos que logré armar para recorrer Json. De esta forma, habría que hacer métodos para c/u de los JSON ----
    // ---- Quizá con Genéricos se podría usar un mismo método para todos los Json de las distintas clases. ----



    // metodo para escribir en un json una lista de Users.

    public static <T> void writeToJson (String file, List<T> list)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File f = new File(file);
            FileWriter fileWriter = new FileWriter(file);
            SequenceWriter sequenceWriter = mapper.writerWithDefaultPrettyPrinter().writeValuesAsArray(fileWriter);
            sequenceWriter.writeAll(list);
            sequenceWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Hubo un error: " + e.getMessage());
        }
    }

    // metodo para leer e imprimir un json de objetos User.
    public static void printJsonUser(String file)
    {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User[] userArray= objectMapper.readValue(new File(file),User[].class);
            List<User> personaList = new ArrayList(Arrays.asList(userArray));

            for (User u : personaList)
            {
                System.out.println(u);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<User> readJsonUser(String file) //No se puede generalizar por ser necesario obtener .class
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            User[] userArray= objectMapper.readValue(new File(file),User[].class);
            System.out.println(Arrays.toString(userArray));
            List<User> userList = new ArrayList(Arrays.asList(userArray));
            return userList;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<String,User> hashMapFromJson(String file)
    {
        List<User> users = readJsonUser(file);
        HashMap<String, User> loginInfo = new HashMap<>();
        for (User user: users) {
            loginInfo.put(user.getWalletId(), user);
        }
        return loginInfo;
    }

    public static void hashMapToJson(String file,HashMap<String, User> hashMap)
    {
        List<User> users = new ArrayList<>();
        hashMap.forEach((k, v) -> {
            users.add(v);
        });
        writeToJson(JsonManager.JSON_USERS,users);
    }


    public static List<Transaction> readJsonTransfer(String file)
    {
        File f = new File(file);
        if (f.exists())
        {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Transaction[] pendingArray= objectMapper.readValue(new File(file),Transaction[].class);
                List<Transaction> pendingList = new ArrayList(Arrays.asList(pendingArray));
                return pendingList;
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    ///Este seria el prototipo de la validacion de las transferencias. Hay que mejorarlo mucho todavia
    ///Podemos hacer varios metodos primero para achicar lo que seria el metodo de validacion.

    /*public void validationTransfer (String file){
        List<Transaction> listPending = readJsonPendingTransfer(file);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Transaction transfer: listPending) {
                    if (transfer.getStatus() == Status.PENDING && transfer.getValidationCounter()<4){
                        List<User> listUser = readJsonUser(file);
                        for (User userBlock: listUser) {
                            if(userBlock.trueBlock == true)
                                transfer.setValidationCounter(transfer.getValidationCounter()+1);
                        }
                    }
                }
            }
        }, 200,1000);
    }*/

    // searchUser implementando java 8
    public static User searchUserByIdWallet(String file, String walletId) {
        List<User> list = readJsonUser(file);
        User user = list.stream().filter(us -> us.getWalletId().equals(walletId)).findFirst().orElse(null);
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
