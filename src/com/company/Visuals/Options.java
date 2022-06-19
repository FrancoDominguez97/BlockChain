package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options implements ActionListener {
    JFrame frame = new JFrame();

    JButton back = new JButton();

    JLabel jlabel = new JLabel("En progreso");

    String userConnected;

    public Options(String UserID) {
        this.userConnected = UserID;
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);

        jlabel.setBounds(50,150,200,80);
        jlabel.setFont((new Font(null,Font.PLAIN,30)));
        back.setBounds(0,0, 50,25);

        back.addActionListener(this);

        back.setFocusable(false);

        frame.add(jlabel);
        frame.add(back);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back){
            frame.dispose();
            User user = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
            user.showMenu().show();
        }
    }
}
