package com.company.JSON;

import com.company.Transferencias.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JsonTransaction extends JsonManager{

    public static void updateTransaction (Transaction transaction){
        List<Transaction> transactions = readJsonTransfer(JsonManager.JSON_PENDING_TRANSACTIONS);
        // funciona igual que un foreach
        List<Transaction> transactions1 = transactions.stream()
                .map(t -> {
                    if (t.getId().toString().equals(transaction.getId().toString()))
                        t = transaction;
                    return t;
                })
                .collect(Collectors.toList());

        writeToJson(JsonManager.JSON_PENDING_TRANSACTIONS,transactions1);
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
        return new ArrayList<>();
    }
    public static List<Transaction> printBlockChain() {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Transaction> blockChain = objectMapper.readValue(new File(JsonTransaction.JSON_BLOCKCHAIN), new TypeReference<List<Transaction>>(){});
            return blockChain;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public static List<Transaction> printPendings() {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Transaction> blockChain = objectMapper.readValue(new File(JsonTransaction.JSON_PENDING_TRANSACTIONS), new TypeReference<List<Transaction>>(){});
            return blockChain;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
