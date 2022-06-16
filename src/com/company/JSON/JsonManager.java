package com.company.JSON;

import com.company.Transferencias.Transaction;
import com.company.Usuarios.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonManager {

    public static final String JSON_BLOCKCHAIN = "blockchain.json"; //Archivo de transacciones ya validadas (el blockchain en sí)
    public static final String JSON_USERS = "users.json"; //Archivo con registros de todos los usuarios
    public static final String JSON_PENDING_TRANSACTIONS = "pending_transactions.json"; //Archivo con registros de transacciones pendientes (todavía no fueron validadas por completo)
    public static final String JSON_CANCELLED_TRANSACTIONS = "cancelled_transactions.json";
    // ---- Agrego esto por acá, son los métodos que logré armar para recorrer Json. De esta forma, habría que hacer métodos para c/u de los JSON ----
    // ---- Quizá con Genéricos se podría usar un mismo método para todos los Json de las distintas clases. ----

    // metodo para escribir en un json una lista de Users.

    public <T> void writeToJson (String file, List<T> list)
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
    public void printJsonUser(String file)
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

    public List<User> readJsonUser(String file)
    {
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
    public List<Transaction> readJsonPendingTransfer(String file)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Transaction[] pendingArray= objectMapper.readValue(new File(file),Transaction[].class);
            List<Transaction> pendingList = new ArrayList(Arrays.asList(pendingArray));
            return pendingList;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
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
}
