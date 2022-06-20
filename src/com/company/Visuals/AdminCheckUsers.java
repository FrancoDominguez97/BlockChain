package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class AdminCheckUsers implements ActionListener {

    JFrame frame = new JFrame();
    JFrame windowUserDate = new JFrame();
    JLabel userDateLabel = new JLabel();
    JTextPane userDateText = new JTextPane();
    JScrollBar userDateVertical = new JScrollBar();
    JScrollPane userDateScroll = new JScrollPane();



    String[] options;

    JButton back = new JButton();
    JButton search = new JButton("Buscar");
    JButton delete = new JButton("Eliminar");
    JTextField datos = new JTextField();
    JComboBox<String> userList;

    String userConnected;
    public AdminCheckUsers(String userID) {
        this.userConnected = userID;
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        windowUserDate.setIconImage(image.getImage());
        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);
        options = this.loadStringFromList(JsonUser.readJsonUser(JsonManager.JSON_USERS));
        userList = new JComboBox<String>((options));

        back.setBounds(0,0, 50,25);
        search.setBounds(260,60,90,25);
        userList.setBounds(60, 60, 200, 25);
        delete.setBounds(350,60,90,25);
        datos.setBounds(100,200,300,250);

        //Ventana User
        userDateLabel.setBounds(30,30,400,200);

        frame.add(userDateLabel);
        frame.add(userDateScroll);
        frame.add(userDateVertical);
        frame.add(userDateText);


        windowUserDate.setVisible(false);
        userDateLabel.setVisible(false);
        userDateScroll.setVisible(false);
        userDateVertical.setVisible(false);
        userDateText.setVisible(false);

        userDateLabel.setBounds(30,30,400,200);
        userDateLabel.setVisible(false);
        userDateText.setBounds(10,20,400,800);
        userDateText.setVisible(false);
        userDateText.setEditable(false);
        windowUserDate.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        windowUserDate.add(userDateLabel);
        windowUserDate.setBounds(10,160,440,280);
        windowUserDate.setLayout(null);
        windowUserDate.setVisible(false);

        windowUserDate.add(userDateText);
        windowUserDate.getContentPane().setLayout(new BorderLayout());
        userDateScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        userDateScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        userDateScroll.setVerticalScrollBar(userDateVertical);
        userDateScroll.setViewportView(userDateText);
        windowUserDate.getContentPane().add(userDateScroll);
        windowUserDate.setResizable(false);
        //Fin Ventana User


        back.addActionListener(this);
        search.addActionListener(this);
        delete.addActionListener(this);

        frame.add(back);
        frame.add(search);
        frame.add(userList);
        frame.add(delete);
        frame.add(datos);

        delete.setFocusable(false);
        search.setFocusable(false);
        datos.setVisible(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(516,538);
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
        User user = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
        if (e.getSource() == search){
            User aux = JsonUser.searchUserByUserName(JsonManager.JSON_USERS, Objects.requireNonNull(userList.getSelectedItem()).toString());
            //if((userList.getSelectedItem())){
            windowUserDate.setVisible(true);
            userDateLabel.setVisible(true);
            userDateScroll.setVisible(true);
            userDateVertical.setVisible(true);
            userDateText.setText(aux.toString());
            userDateText.setVisible(true);
            //}
        }
        if (e.getSource() == back){
            frame.dispose();
            user.obtenerMenu().show();
        }
        if (e.getSource() == delete){
            datos.setVisible(false);
            //elimina el usuario del archivo json.

        }
    }
}