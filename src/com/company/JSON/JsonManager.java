package com.company.JSON;

import com.company.Transferencias.Transaction;
import com.company.Usuarios.User;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonManager {

    public static final String JSON_BLOCKCHAIN = "accepted.json"; //Archivo de transacciones ya validadas (el blockchain en sí)
    public static final String JSON_USERS = "users.json"; //Archivo con registros de todos los usuarios
    public static final String JSON_PENDING_TRANSACTIONS = "pending_transactions.json"; //Archivo con registros de transacciones pendientes (todavía no fueron validadas por completo)
    public static final String JSON_CANCELLED_TRANSACTIONS = "cancelled_transactions.json";
    // ---- Agrego esto por acá, son los métodos que logré armar para recorrer Json. De esta forma, habría que hacer métodos para c/u de los JSON ----
    // ---- Quizá con Genéricos se podría usar un mismo método para todos los Json de las distintas clases. ----
    // metodo para escribir en un json una lista de Users.
    public static <T> void writeToJson(String file, List<T> list) {
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