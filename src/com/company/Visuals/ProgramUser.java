package com.company.Visuals;

import com.company.Interfaces.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ProgramUser implements Menu, ActionListener {

    JFrame frame = new JFrame();

    JButton transferencias = new JButton();
    JButton opcionesUser = new JButton();
    JButton minar = new JButton();
    JButton verMontos = new JButton();
    JButton cerrarSesion = new JButton();
    String userConnected;


    public ProgramUser(String userID)
    {
        this.userConnected = userID;
    }




    @Override
    public void show() {
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        transferencias.setBounds(0,0, 250,200);
        opcionesUser.setBounds(250,0,250,200);
        minar.setBounds(0,200,250,200);
        verMontos.setBounds(250,200,250,200);
        cerrarSesion.setBounds(0,400,500,100);

        transferencias.setText("Transferencias");
        transferencias.setFont(new Font(null,Font.ITALIC,25));
        opcionesUser.setText("Opciones Usuario");
        opcionesUser.setFont(new Font(null,Font.ITALIC,25));
        minar.setText("Minar");
        minar.setFont(new Font(null,Font.ITALIC,25));
        verMontos.setText("Ver Montos");
        verMontos.setFont(new Font(null,Font.ITALIC,25));
        cerrarSesion.setText("Cerrar Sesion");
        cerrarSesion.setFont(new Font(null,Font.ITALIC,25));

        transferencias.setFocusable(false);
        opcionesUser.setFocusable(false);
        minar.setFocusable(false);
        verMontos.setFocusable(false);
        cerrarSesion.setFocusable(false);

        transferencias.addActionListener(this);
        opcionesUser.addActionListener(this);
        minar.addActionListener(this);
        verMontos.addActionListener(this);
        cerrarSesion.addActionListener(this);

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
            frame.dispose();
            Options options = new Options(userConnected);
            //codigo de opcionesUser
        }

        if (e.getSource() == minar){
            //codigo de validaciones de blockchains
        }

        if (e.getSource() == verMontos){
            frame.dispose();
            CheckAmounts checkAmounts = new CheckAmounts(userConnected);
        }

        if (e.getSource() == cerrarSesion){
            frame.dispose();
            LoginPage loginPage = new LoginPage();
        }
    }

}
