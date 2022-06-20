package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;
import com.company.enums.CoinName;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Objects;

public class CheckAmounts implements ActionListener {
    JFrame frame = new JFrame();

    String[] options = {CoinName.UTNCOIN.name(),CoinName.BITCOIN.name(),CoinName.ETHEREUM.name(),CoinName.LUNA.name()};

    JComboBox<String> jComboBox = new JComboBox<>(options);
    JComboBox<String> swapComboBox = new JComboBox<>(options);
    JButton search = new JButton("Buscar");
    JLabel jLabel = new JLabel();
    JLabel messageLabel = new JLabel();
    JLabel messageUSD = new JLabel();
    JButton back = new JButton();
    JButton swap = new JButton("Transformar");
    JButton confirmSwap = new JButton("Confirmar");
    JLabel swapText = new JLabel();
    JTextField swapBox = new JTextField();
    String userConnected;

    public CheckAmounts(String userID) {
        this.userConnected = userID;
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);


        jComboBox.setBounds(80, 50, 140, 20);
        swapComboBox.setBounds(100, 320, 140, 20);
        back.setBounds(0,0, 50,25);
        messageLabel.setBounds(90, 125, 400,100);
        messageUSD.setBounds(90, 150, 400,100);
        search.setBounds(100, 100, 90, 20);
        confirmSwap.setBounds(100,350,90,20);

        swapText.setBounds(100,260,250,20);
        swapText.setText("Ingrese valor a cambiar");
        swapBox.setBounds(100,290,130,20);

        jLabel.setBounds(90, 100, 400, 100);

        frame.add(swap);
        frame.add(jComboBox);
        frame.add(search);
        frame.add(jLabel);
        frame.add(messageLabel);
        frame.add(messageUSD);
        frame.add(back);
        frame.add(swapBox);
        frame.add(swapText);
        frame.add(swapComboBox);
        frame.add(confirmSwap);

        swap.setVisible(false);
        swap.setBounds(100,220,130,30);
        swap.addActionListener(this);

        search.addActionListener(this);
        back.addActionListener(this);
        confirmSwap.addActionListener(this);

        back.setFocusable(false);
        confirmSwap.setFocusable(false);
        swap.setFocusable(false);

        confirmSwap.setVisible(false);
        swapComboBox.setVisible(false);
        swapBox.setVisible(false);
        swapText.setVisible(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User user = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
        if (e.getSource() == search) {
            String selected = "Seleccionaste: " + jComboBox.getItemAt(jComboBox.getSelectedIndex());
            jLabel.setText(selected);
            messageLabel.setText("Tenes: " + user.getCoin(jComboBox.getItemAt(jComboBox.getSelectedIndex())));
            messageUSD.setText("Valor en U$D: " + new DecimalFormat("#.00000").format(user.getCoin(jComboBox.getItemAt(jComboBox.getSelectedIndex())).calculateValueInUSD()));
            swap.setVisible(true);
        }
        if (e.getSource() == back){
            frame.dispose();
            user.obtenerMenu().show();
        }
        if (e.getSource() == swap) {
            confirmSwap.setVisible(true);
            swapComboBox.setVisible(true);
            swapBox.setVisible(true);
            swapText.setVisible(true);

        }
        if(e.getSource() == confirmSwap){
            //el codigo del swapeo de monedas.
            double amount = Double.parseDouble(swapBox.getText());
            System.out.println(amount);
            user.swapCoin(jComboBox.getItemAt(jComboBox.getSelectedIndex()),swapComboBox.getItemAt(swapComboBox.getSelectedIndex()),amount);
        }

    }
}

