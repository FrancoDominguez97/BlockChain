package com.company.JSON;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonManager {

    public static final String JSON_BLOCKCHAIN = "blockchain.json"; //Archivo de transacciones ya validadas (el blockchain en sí)
    public static final String JSON_USERS = "users.json"; //Archivo con registros de todos los usuarios
    public static final String JSON_PENDING_TRANSACTIONS = "pending_transactions.json"; //Archivo con registros de transacciones pendientes (todavía no fueron validadas por completo)

    // metodo para escribir en un json una lista generica.
    public static <T> void writeToJson(String file, List<T> list) {
        if (list.isEmpty())
            list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            File f = new File(file);
            FileWriter fileWriter = new FileWriter(file);
            SequenceWriter sequenceWriter = mapper.writerWithDefaultPrettyPrinter().writeValuesAsArray(fileWriter);
            sequenceWriter.writeAll(list);
            sequenceWriter.close();
        } catch (IOException e) {
            System.out.println("Hubo un error: " + e.getMessage());
        }
    }
}
