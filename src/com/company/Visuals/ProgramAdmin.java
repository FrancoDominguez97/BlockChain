package com.company.Visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramAdmin implements Menu, ActionListener {

    JFrame frame = new JFrame();

    JButton verUsers = new JButton();
    JButton changeValue = new JButton();
    JButton coinMovements = new JButton();
    JButton verTransactions = new JButton();
    JButton cerrarSesion = new JButton();



    String userConnected;

    public ProgramAdmin(String userID)
    {
        this.userConnected = userID;
    }


    @Override
    public void show() {

        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        verUsers.setBounds(0,0, 250,200);
        changeValue.setBounds(250,0,250,200);
        coinMovements.setBounds(0,200,250,200);
        verTransactions.setBounds(250,200,250,200);
        cerrarSesion.setBounds(0,400,500,100);

        verUsers.setText("Ver Users"); // con lista desplegable por usuario, cuando selecciona un user le tira todos los datos. Ahi mismo que haya un boton de borrado de user.
        verUsers.setFont(new Font(null,Font.ITALIC,25));
        changeValue.setText("Cambiar valor de coin");
        changeValue.setFont(new Font(null,Font.ITALIC,20));
        coinMovements.setText("Agregar/Retirar coin");
        coinMovements.setFont(new Font(null,Font.ITALIC,20));
        verTransactions.setText("Ver transacciones");
        verTransactions.setFont(new Font(null,Font.ITALIC,25));
        cerrarSesion.setText("Cerrar Sesion");
        cerrarSesion.setFont(new Font(null,Font.ITALIC,25));

        verUsers.setFocusable(false);
        changeValue.setFocusable(false);
        coinMovements.setFocusable(false);
        verTransactions.setFocusable(false);
        cerrarSesion.setFocusable(false);

        verUsers.addActionListener(this);
        changeValue.addActionListener(this);
        coinMovements.addActionListener(this);
        verTransactions.addActionListener(this);
        cerrarSesion.addActionListener(this);

        frame.add(verUsers);
        frame.add(changeValue);
        frame.add(coinMovements);
        frame.add(verTransactions);
        frame.add(cerrarSesion);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(516,538);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //wip
        if (e.getSource() == verUsers){
            frame.dispose();
            AdminCheckUsers adminCheckUsers = new AdminCheckUsers(userConnected);
        }

        if (e.getSource() == changeValue){
            frame.dispose();

            //codigo de cambio de valor de coin
        }

        if (e.getSource() == coinMovements){
            //codigo de agregado y retirado de coins
        }

        if (e.getSource() == verTransactions){
            frame.dispose();
            AdminTransactionsList adminTransactionsList = new AdminTransactionsList(userConnected);
        }

        if (e.getSource() == cerrarSesion){
            frame.dispose();
            LoginPage loginPage = new LoginPage();
        }
    }


}
