package com.company.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Program implements ActionListener {

    JFrame frame = new JFrame();
    JLabel programLabel = new JLabel();
    JButton transferencias = new JButton();
    JButton opcionesUser = new JButton();
    JButton minar = new JButton();
    JButton verMontos = new JButton();
    JButton cerrarSesion = new JButton();

    Program(String userID){
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        programLabel.setBounds(50,50, 200,35);
        programLabel.setFont(new Font(null,Font.ITALIC,25));
        programLabel.setText("Bienvenido, "+ userID +"!.");

        transferencias.setBounds(0,0, 250,200);
        opcionesUser.setBounds(250,0,250,200);
        minar.setBounds(0,200,250,200);
        verMontos.setBounds(250,200,250,200);
        cerrarSesion.setBounds(0,400,500,100);

        frame.add(programLabel);
        frame.add(transferencias);
        frame.add(opcionesUser);
        frame.add(minar);
        frame.add(verMontos);
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
        if (e.getSource() == transferencias){
            //codigo de transferencias
        }

        if (e.getSource() == opcionesUser){
            //codigo de opcionesUser
        }

        if (e.getSource() == minar){
            //codigo de validaciones de blockchains
        }

        if (e.getSource() == verMontos){
            //codigo de ver saldos, billetera y eso
        }

        if (e.getSource() == cerrarSesion){
            LoginData loginData = new LoginData();
            LoginPage loginPage = new LoginPage(loginData.getLoginInfo());

        }
    }
}