package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonManager {

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
}
