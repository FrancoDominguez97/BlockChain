package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminCheckUsers implements ActionListener {

    JFrame frame = new JFrame();

    String[] options;

    JButton back = new JButton();
    JButton search = new JButton("Buscar");
    JButton delete = new JButton("Eliminar");
    JLabel datos = new JLabel("Ejemplo");
    JComboBox<String> userList;

    String userConnected;
    public AdminCheckUsers(String userID) {
        this.userConnected = userID;
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);
        options = this.loadStringFromList(JsonUser.readJsonUser(JsonManager.JSON_USERS));
        userList = new JComboBox<String>((options));

        back.setBounds(0,0, 50,25);
        search.setBounds(260,60,90,25);
        userList.setBounds(60, 60, 200, 25);
        delete.setBounds(350,60,90,25);
        datos.setBounds(150,200,300,25);


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
            //busca los datos del usuario seleccionado y los muestra por pantalla
            if((userList.getSelectedItem()) == user.getWalletId()){
                datos.setText(user.toString());
                datos.setVisible(true);
            }
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