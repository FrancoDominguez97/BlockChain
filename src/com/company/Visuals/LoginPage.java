package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");

    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JPasswordField UUIDpassField = new JPasswordField();

    JLabel userIDLabel = new JLabel("UserName");
    JLabel userPasswordLabel = new JLabel("Password");
    JLabel UUIDpass = new JLabel("UUID pass");
    JLabel messageLabel = new JLabel();

    HashMap<String,User> loginfo = new HashMap<String,User>();

    public LoginPage(){ // Al pastear datos en los campos del login, arroja excepciones no catcheables desde acá. Según investigamos, dependen de la JVM y es un bug conocido, supuestamente corregido en versiones más nuevas de IDEA.

        loginfo = JsonUser.hashMapFromJson(JsonManager.JSON_USERS);

        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        UUIDpass.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);
        UUIDpassField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);
        userIDField.setBounds(125,200,200,25);
        userIDLabel.setBounds(50,200,200,25);

        messageLabel.setBounds(125,350,250,35);
        messageLabel.setFont(new Font(null,Font.PLAIN,25));

        loginButton.setBounds(125,250,100,25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);

        registerButton.setBounds(225,250,100,25);
        registerButton.addActionListener(this);
        registerButton.setFocusable(false);

        frame.add(userIDLabel);
        frame.add(userIDField);
        frame.add(userPasswordLabel);
        frame.add(userPasswordField);
        frame.add(UUIDpass);
        frame.add(UUIDpassField);
        frame.add(messageLabel);
        frame.add(loginButton);
        frame.add(registerButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

            if (e.getSource()==registerButton){
                User user = new User();
                frame.dispose();
                user.register();
            }
            if(e.getSource()==loginButton){

                String uuidPass = String.valueOf(UUIDpassField.getPassword());
                String pass = String.valueOf(userPasswordField.getPassword());
                String userID = userIDField.getText();
                if(loginfo.containsKey(uuidPass)){
                    User auxUser = loginfo.get(uuidPass);
                    if(auxUser.getPassword().equals(pass) && auxUser.getUserName().equals(userID)){
                        messageLabel.setForeground(Color.green);
                        messageLabel.setText("Login exitoso");
                        frame.dispose();

                        auxUser.obtenerMenu().show();

                    }
                    else{
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("Contraseña incorrecta");
                    }
                }
                else{
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Usuario incorrecto");
                }
            }

    }
}
