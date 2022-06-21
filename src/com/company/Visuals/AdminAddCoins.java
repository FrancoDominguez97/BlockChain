package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;
import com.company.enums.CoinName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminAddCoins implements ActionListener {
    JFrame frame = new JFrame();

    String[] options = {CoinName.UTNCOIN.name(), CoinName.BITCOIN.name(), CoinName.ETHEREUM.name(), CoinName.LUNA.name()};

    JComboBox<String> jComboBox = new JComboBox<>(options);
    JButton back = new JButton();
    JButton search = new JButton("Seleccionar");
    JButton confirm = new JButton("Agregar");
    JLabel jLabel = new JLabel("Ingrese monto:");
    JTextField amountField = new JTextField();

    String[] usuarios;
    JComboBox<String> userList;

    String userConnected;

    public AdminAddCoins(String userID) {
        this.userConnected = userID;

        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);

        usuarios = loadStringFromList(JsonUser.readJsonUser(JsonManager.JSON_USERS));
        userList = new JComboBox<String>((usuarios));

        back.setBounds(0, 0, 50, 25);
        search.setBounds(260,60,140,25);
        userList.setBounds(60, 60, 200, 25);
        jComboBox.setBounds(60, 150, 200, 25);
        amountField.setBounds(60,200,100,25);
        confirm.setBounds(260,240,140,25);
        jLabel.setBounds(60, 100, 200, 25);

        jLabel.setFont(new Font(null,Font.PLAIN,20));

        frame.add(back);
        frame.add(userList);
        frame.add(confirm);
        frame.add(search);
        frame.add(jComboBox);
        frame.add(amountField);
        frame.add(jLabel);

        search.setFocusable(false);
        confirm.setFocusable(false);

        jComboBox.setVisible(false);
        amountField.setVisible(false);
        confirm.setVisible(false);
        jLabel.setVisible(false);

        back.addActionListener(this);
        search.addActionListener(this);
        confirm.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(516, 538);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private String[] loadStringFromList(List<User> list){
        String[] options = new String[list.size()];
        int i = 0;
        for (User u: list) {
            options[i] = u.getUserName();
            i++;
        }
        return options;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User user = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS, userConnected);
        if (e.getSource() == back) {
            frame.dispose();
            user.obtenerMenu().show();
        }
        if (e.getSource() == search){
            jComboBox.setVisible(true);
            amountField.setVisible(true);
            confirm.setVisible(true);
            jLabel.setVisible(true);
        }
        if (e.getSource() == confirm){
            if (JsonUser.addAmountToUser(userList.getItemAt(userList.getSelectedIndex()),jComboBox.getItemAt(jComboBox.getSelectedIndex()), Double.parseDouble(amountField.getText())))
            {
                JOptionPane.showMessageDialog(null,"Monto modificado correctamente.");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No hay monto suficiente.");
            }
        }
    }
}