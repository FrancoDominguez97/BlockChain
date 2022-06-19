package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.Transferencias.Transaction;
import com.company.Usuarios.Coin;
import com.company.Usuarios.User;
import com.company.enums.CoinName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Minar implements ActionListener{

    private double reward = 15.0;

    // produce menu visual al tocar boton de MINAR.
    // "Comenzar minado" boton que corra metodo de minado. Donde recorra todas las transacciones pendientes del Json
    //y valide una por una (si no fue validada previamente por el mismo user). Si alguna transaccion llega a 3 validaciones,
    //que la pase al archivo de aceptadas (blockchain) y la elimine de pendientes. Que se premie al user que mina en UTNcoin

    //


        JFrame frame = new JFrame();
        JLabel text = new JLabel("Presione el boton para minar");
        JButton startDigging = new JButton("Minar!");
        JButton back = new JButton();
        JLabel label = new JLabel("No hay Transacciones Pendientes de Validacion.");

        String userConnected;
        public Minar(String userID) {
            this.userConnected = userID;

            label.setBounds(50,400,400,35);
            label.setFont((new Font(null,Font.BOLD,17)));
            label.setVisible(false);
            frame.add(label);

            frame.setTitle("TP FINAL LABORATORIO 3");
            ImageIcon image = new ImageIcon("logo.png");
            frame.setIconImage(image.getImage());

            ImageIcon backImage = new ImageIcon("flecha.png");
            back.setIcon(backImage);

            back.setBounds(0,0, 50,25);
            startDigging.setBounds(100, 150, 250, 150);
            text.setBounds(100,50,400,35);

            startDigging.setFont((new Font(null,Font.PLAIN,30)));
            text.setFont((new Font(null,Font.PLAIN,20)));

            startDigging.addActionListener(this);
            back.addActionListener(this);

            startDigging.setFocusable(false);
            back.setFocusable(false);

            frame.add(back);
            frame.add(startDigging);
            frame.add(text);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLayout(null);
            frame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startDigging) {
                List<Transaction> pendingTransactions = JsonManager.readJsonTransfer(JsonManager.JSON_PENDING_TRANSACTIONS);

                if(pendingTransactions!= null)
                {
                    int countFullyValidated = 0;
                    double totalReward = 0.0;
                    for (Transaction t : pendingTransactions)
                    {
                        if(!t.checkValidated(userConnected))
                        {
                            countFullyValidated += t.validate(userConnected);
                        }
                    }
                    totalReward = (double) countFullyValidated * reward;
                    User user = JsonManager.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
                    user.getWallet().searchCoinByName(String.valueOf(CoinName.UTNCOIN)).setAmount(user.getWallet().searchCoinByName(String.valueOf(CoinName.UTNCOIN)).getAmount() + totalReward);
                    JsonManager.updateUser(user);
                }
                else
                {
                    // imprimir "no hay transacciones pendientes"
                    label.setVisible(true);
                }
            }
            if (e.getSource() == back){
                frame.dispose();
                User user = JsonManager.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
                user.obtenerMenu().show();
            }
        }
    }


