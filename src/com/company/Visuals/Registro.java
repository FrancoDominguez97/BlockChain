package com.company.Visuals;


import com.company.JSON.JsonManager;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Registro implements ActionListener {

    JFrame frame = new JFrame();

    JLabel nameVisual = new JLabel("Nombre");
    JTextField nameField = new JTextField();
    JLabel lastNameVisual = new JLabel("Apellido");
    JTextField lastNameField = new JTextField();
    JLabel userNameVisual = new JLabel("Username");
    JTextField userNameField = new JTextField();
    JLabel passVisual = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField();
    JLabel dateVisual = new JLabel("FechaNacimiento");
    JTextField dateField = new JTextField();
    JLabel emailVisual = new JLabel("Email");
    JTextField emailField = new JTextField();

    JButton guardar = new JButton();
    JButton limpiar = new JButton();
    JButton back = new JButton();

    HashMap<String,User> loginfo = new HashMap<>();

    public Registro() {

        loginfo = JsonUser.hashMapFromJson(JsonManager.JSON_USERS);
        
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);

        nameVisual.setBounds(50, 40, 75, 25);
        nameField.setBounds(160, 40, 200, 25);

        lastNameVisual.setBounds(50, 80, 75, 25);
        lastNameField.setBounds(160, 80, 200, 25);

        dateVisual.setBounds(50, 120, 150, 25);
        dateField.setBounds(160, 120, 200, 25);

        userNameVisual.setBounds(50, 160, 75, 25);
        userNameField.setBounds(160, 160, 200, 25);

        passVisual.setBounds(50, 200, 75, 25);
        passwordField.setBounds(160, 200, 200, 25);

        emailVisual.setBounds(50, 240, 75, 25);
        emailField.setBounds(160, 240, 200, 25);

        guardar.setBounds(160, 300, 100, 25);
        guardar.addActionListener(this);
        guardar.setText("Guardar");
        guardar.setFocusable(false);

        limpiar.setBounds(260, 300, 100, 25);
        limpiar.addActionListener(this);
        limpiar.setText("Limpiar");
        limpiar.setFocusable(false);

        back.setBounds(0,0, 50,25);
        back.addActionListener(this);

        back.setFocusable(false);


        frame.add(nameVisual);
        frame.add(nameField);

        frame.add(lastNameVisual);
        frame.add(lastNameField);

        frame.add(passVisual);
        frame.add(passwordField);

        frame.add(userNameVisual);
        frame.add(userNameField);

        frame.add(dateVisual);
        frame.add(dateField);

        frame.add(emailVisual);
        frame.add(emailField);

        frame.add(guardar);
        frame.add(limpiar);
        frame.add(back);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==guardar){
            User user = new User();
            JsonManager admin = new JsonManager(); // este admin es para que me deje guardar en el archivo JSON
            //aca creo la lista para guardar el nuevo dato ahi y pasarlo al archivo JSON.
            List<User> users = new ArrayList<>();


            user.setName(nameField.getText());
            user.setLastName(lastNameField.getText());
            user.setDateOfBirth(dateField.getText());
            if (user.calcutaleAge((user.getDateOfBirth()))== false) {
                JOptionPane.showMessageDialog(null, "Usted es menor de edad, no se puede registrar.");;
                System.exit(1);
            }
            user.setUserName(userNameField.getText());
            //searchUserName(userName); Aca realizar una funcion para que busque si no esta en uso el username.
            user.setEmail(emailField.getText());
            if(user.emailVerify(user.getEmail()) == false){
                JOptionPane.showMessageDialog(null, "Mail invalido, reingrese:");
                user.setEmail(emailField.getText());
            }
            //searchMail(email); Aca realizar una funcion para que busque si esta en uso o no el mail, basicamente es la misma q para username.
            user.setPassword(String.valueOf(passwordField.getPassword()));

            user.getWallet().initialize();

            loginfo.put(user.getWalletId(),user);
         /*   loginfo.forEach((k, v) -> {
                System.out.format("key: %s, value: %s", k,v); //Imprimir el hashmap para debuggeo
            });*/

            JsonUser.hashMapToJson(JsonManager.JSON_USERS,loginfo);
        }
        if (e.getSource()==limpiar){
            nameField.setText("");
            lastNameField.setText("");
            passwordField.setText("");
            userNameField.setText("");
            emailField.setText("");
            dateField.setText("");
        }
        if (e.getSource() == back){
            frame.dispose();
            //LoginData loginData = new LoginData();
            LoginPage loginPage = new LoginPage();
        }
    }
}
