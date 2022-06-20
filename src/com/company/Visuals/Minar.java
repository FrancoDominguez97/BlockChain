package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonTransaction;
import com.company.JSON.JsonUser;
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
        JLabel messageMine = new JLabel("Minado Finalizado!");

        String userConnected;
        public Minar(String userID) {
            this.userConnected = userID;

            label.setBounds(50,400,400,35);
            label.setFont((new Font(null,Font.BOLD,17)));
            label.setVisible(false);
            frame.add(label);
            frame.add(messageMine);

            frame.setTitle("TP FINAL LABORATORIO 3");
            ImageIcon image = new ImageIcon("logo.png");
            frame.setIconImage(image.getImage());

            ImageIcon backImage = new ImageIcon("flecha.png");
            back.setIcon(backImage);

            back.setBounds(0,0, 50,25);
            startDigging.setBounds(100, 150, 250, 150);
            text.setBounds(100,50,400,35);
            messageMine.setBounds(100,380,400,35);

            messageMine.setFont((new Font(null,Font.PLAIN,30)));
            messageMine.setVisible(false);

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
                List<Transaction> pendingTransactions = JsonTransaction.readJsonTransfer(JsonManager.JSON_PENDING_TRANSACTIONS);

                if(!pendingTransactions.isEmpty())
                {
                    System.out.println("Lista no vacia");
                    int countFullyValidated = 0;
                    int countValidatedOnce = 0;
                    double totalReward = 0.0;
                    for (Transaction t : pendingTransactions)
                    {
                        System.out.println("Recorro transactions");
                        if(t.checkValidated(userConnected))
                        {
                            countValidatedOnce+=1;
                            System.out.println("entro validacion");
                            countFullyValidated += t.validate(userConnected);
                        }
                    }
                    if(countValidatedOnce>0)
                    {
                        messageMine.setVisible(true);
                        System.out.println("validatedOnce: "+ countValidatedOnce);
                        totalReward = (double) countFullyValidated * reward;
                        if(totalReward>0)
                        {
                            JOptionPane.showMessageDialog(null, "Minado con Éxito! Ha Generado: " + totalReward + "UTN coins!");
                            User user = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
                            user.getWallet().searchCoinByName(CoinName.UTNCOIN.name()).setAmount(user.getWallet().searchCoinByName(CoinName.UTNCOIN.name()).getAmount() + totalReward);
                            JsonUser.updateUser(user);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Las Transacciones Disponibles No Son Aptas de Validacion.");
                    }
                }
                else
                {
                    // imprimir "no hay transacciones pendientes"
                    JOptionPane.showMessageDialog(null, "No Hay Transacciones Para Validar.");
                    //label.setVisible(true);
                }
            }
            if (e.getSource() == back){
                frame.dispose();
                User user = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
                user.obtenerMenu().show();
            }
        }
    }


