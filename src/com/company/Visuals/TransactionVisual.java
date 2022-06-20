package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.Usuarios.User;
import com.company.enums.TransactionToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionVisual implements ActionListener {

    JFrame frame = new JFrame();
    String[] options = {TransactionToDo.SELECCIONE.name(), TransactionToDo.NUEVA_TRANSFERENCIA.name(),TransactionToDo.HISTORIAL_TRANSFERENCIAS.name()};

    public void setOptions(String[] options) {
        this.options = options;
    }

    JComboBox<String> jComboBox = new JComboBox<>(options);
    JButton search = new JButton("Buscar");
    JLabel jLabel = new JLabel();
    JLabel messageLabel = new JLabel();
    JButton back = new JButton();
    JLabel walletIdentificator = new JLabel();

    JLabel ejemplo = new JLabel("Ejemplo1");
    JLabel ejemplo2 = new JLabel("Ejemplo2");
    String userConnected;

    public TransactionVisual(String userID) {
        this.userConnected = userID;
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);

        jComboBox.setBounds(80, 50, 250, 25);
        back.setBounds(0,0, 50,25);
        search.setBounds(100, 100, 90, 20);

        walletIdentificator.setBounds(70,20,400,25);
        walletIdentificator.setText("Wallet ID: " + userID);
        walletIdentificator.setFont((new Font(null, Font.PLAIN,15)));

        ejemplo.setBounds(100, 275, 90, 20);
        ejemplo2.setBounds(100, 300, 90, 20);

        frame.add(back);
        frame.add(jComboBox);
        frame.add(search);
        frame.add(walletIdentificator);

        frame.add(ejemplo);
        frame.add(ejemplo2);

        ejemplo.setVisible(false);
        ejemplo2.setVisible(false);

        search.addActionListener(this);
        back.addActionListener(this);

        back.setFocusable(false);
        search.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search){
            JsonManager admin = new JsonManager();
            User user = admin.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
            if(jComboBox.getItemAt(jComboBox.getSelectedIndex()) == TransactionToDo.NUEVA_TRANSFERENCIA.name()){
                //NUEVA TRANSACCION
                String selected = "Nueva transaccion:";
                jLabel.setText(selected);
                jLabel.setVisible(true);
                ejemplo.setVisible(true);
            }
            if(jComboBox.getItemAt(jComboBox.getSelectedIndex()) == TransactionToDo.HISTORIAL_TRANSFERENCIAS.name()){
                //HISTORIAL DE TRANSACCIONES
                String selected = "Historial de transacciones:";
                jLabel.setText(selected);
                jLabel.setVisible(true);
                ejemplo2.setVisible(true);
            }
            else{
                ejemplo.setVisible(false);
                ejemplo2.setVisible(false);
                jLabel.setVisible(false);
            }
        }
        if (e.getSource() == back){
            frame.dispose();
            User user = JsonManager.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
            user.obtenerMenu().show();
        }
    }
}