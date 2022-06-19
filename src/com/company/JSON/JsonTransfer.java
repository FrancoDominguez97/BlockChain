package com.company.JSON;

import com.company.Interfaces.Json_Transfer;
import com.company.Transferencias.Transaction;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonTransfer extends JsonManager implements Json_Transfer {

    public JsonTransfer() {
    }

    public static List<Transaction> JsonToListTransfer(String file) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
        try {
            Transaction[]array = objectMapper.readValue(new File(file), Transaction[].class);
            List<Transaction> list = new ArrayList(Arrays.asList(array));
            return list;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public static Transaction searchByIdSender(String file, String sender) {
        List<Transaction> tranferList = JsonToListTransfer(file);
        for (Transaction show : tranferList){
            if (show.getSender().equals(sender))
                return show;
        }
        return null;
    }
    public static Transaction searchByIdReceiver(String file, String receiver) {
        List<Transaction> tranferList = JsonToListTransfer(file);
        for (Transaction show : tranferList){
            if (show.getReceiver().equals(receiver))
                return show;
        }
        return null;
    }
    public static void showTransactionSender(String file, String sender) {
        List<Transaction> tranferList = JsonToListTransfer(file);
        for (Transaction show : tranferList){
            if (show.getSender().equals(sender))
                System.out.println(show.toString());
        }

    }
    public static void showTransactionReceiver(String file, String receiver) {
        List<Transaction> tranferList = JsonToListTransfer(file);
        for (Transaction show : tranferList){
            if (show.getReceiver().equals(receiver))
                System.out.println(show.toString());
        }
    }
    public static void agregarPendiente(Transaction agregar){
        List<Transaction>lista = JsonTransfer.JsonToListTransfer(JSON_PENDING_TRANSACTIONS);
        if (lista==null) {
            lista = new ArrayList<>();
        }
        lista.add(agregar);
        writeToJson(JSON_PENDING_TRANSACTIONS, lista);
    }
    public static int returnPos(String idTransfer) {
        List<Transaction> lis = new ArrayList<>();
        lis =JsonTransfer.JsonToListTransfer(JsonTransfer.JSON_PENDING_TRANSACTIONS);
        for (Transaction transferFound : lis){
            if (transferFound!=null) {
                if (transferFound.getId().equals(idTransfer))
                    return lis.indexOf(transferFound);
            }
        }
        return -1;
    }
}
