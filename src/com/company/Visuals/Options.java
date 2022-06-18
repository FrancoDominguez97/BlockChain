package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.Usuarios.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options implements ActionListener {
    JFrame frame = new JFrame();

    JButton back = new JButton();
    JButton save = new JButton("Guardar cambios");

    JLabel idWallet = new JLabel();
    JLabel name = new JLabel("Nombre:");
    JLabel lastName = new JLabel("Apellido:");
    JLabel password = new JLabel("Contraseña:");

    JTextField nameField = new JTextField();
    JTextField lastNameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    String userConnected;
    User user = new User();
    public Options(String UserID) {
        this.userConnected = UserID;
        this.user = JsonManager.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);

        idWallet.setBounds(50,40,400,25);
        idWallet.setText("Wallet ID: " + UserID);
        idWallet.setFont((new Font(null,Font.PLAIN,15)));

        name.setBounds(50,100,200,25);
        name.setFont((new Font(null,Font.PLAIN,15)));

        lastName.setBounds(50,150,200,25);
        lastName.setFont((new Font(null,Font.PLAIN,15)));

        password.setBounds(50,200,200,25);
        password.setFont((new Font(null,Font.PLAIN,15)));

        nameField.setBounds(200,100,200,25);
        nameField.setFont((new Font(null,Font.PLAIN,15)));
        nameField.setText(user.getName());

        lastNameField.setBounds(200,150,200,25);
        lastNameField.setFont((new Font(null,Font.PLAIN,15)));
        lastNameField.setText(user.getLastName());

        passwordField.setBounds(200,200,200,25);
        passwordField.setFont((new Font(null,Font.PLAIN,15)));
        passwordField.setText(user.getPassword());

        save.setBounds(200,400, 150,25);
        save.addActionListener(this);
        save.setFocusable(false);

        back.setBounds(0,0, 50,25);
        back.addActionListener(this);
        back.setFocusable(false);

        frame.add(name);
        frame.add(nameField);
        frame.add(password);
        frame.add(passwordField);
        frame.add(lastName);
        frame.add(lastNameField);
        frame.add(back);
        frame.add(idWallet);
        frame.add(save);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back){
            frame.dispose();
            User user = JsonManager.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
            user.obtenerMenu().show();
        }
        if(e.getSource() == save){
            this.user.setName(nameField.getText());
            this.user.setPassword(String.valueOf(passwordField.getPassword()));
            this.user.setLastName(lastNameField.getText());
            JsonManager.updateUser(this.user);
        }
    }
}