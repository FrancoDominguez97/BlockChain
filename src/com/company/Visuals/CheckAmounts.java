package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;
import com.company.enums.CoinName;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckAmounts implements ActionListener {
    JFrame frame = new JFrame();

    String[] options = {CoinName.UTNCOIN.name(),CoinName.BITCOIN.name(),CoinName.ETHEREUM.name(),CoinName.LUNA.name()};

    JComboBox<String> jComboBox = new JComboBox<>(options);
    JButton jButton = new JButton("Buscar");
    JLabel jLabel = new JLabel();
    JLabel messageLabel = new JLabel();
    JButton back = new JButton();

    String userConnected;

    public CheckAmounts(String userID) {
        this.userConnected = userID;
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);

        jComboBox.setBounds(80, 50, 140, 20);
        back.setBounds(0,0, 50,25);
        messageLabel.setBounds(90, 200, 400,100);
        jButton.setBounds(100, 100, 90, 20);

        jLabel.setBounds(90, 100, 400, 100);

        frame.add(jComboBox);
        frame.add(jButton);
        frame.add(jLabel);
        frame.add(messageLabel);
        frame.add(back);

        jButton.addActionListener(this);
        back.addActionListener(this);

        back.setFocusable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton) {
            User user = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
            String selected = "Seleccionaste: " + jComboBox.getItemAt(jComboBox.getSelectedIndex());
            jLabel.setText(selected);
            messageLabel.setText("Tenes: " + user.getCoin(jComboBox.getItemAt(jComboBox.getSelectedIndex())));
        }
        if (e.getSource() == back){
            frame.dispose();
            User user = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
            user.obtenerMenu().show();
        }
    }
}

