package com.company.login;

import com.company.User;

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

    public LoginPage(HashMap<String, User> loginInformation){
        loginfo = loginInformation;

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
            //llamada a registro
        }
        if(e.getSource()==loginButton){

            String uuidPass = String.valueOf(UUIDpassField.getPassword()); //asi seria para comparar tambien el uuid password para el ingreso.
            String pass = String.valueOf(userPasswordField.getPassword());
            String userID = userIDField.getText();
            if(loginfo.containsKey(uuidPass)){
                if(loginfo.get(uuidPass).getPassword().equals(pass) && loginfo.get(uuidPass).getUserName().equals(userID)){
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login exitoso");
                    frame.dispose();
                    Program program = new Program(userID);
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
