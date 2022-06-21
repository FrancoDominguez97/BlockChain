package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;
import com.company.enums.CoinName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminChangeValue implements ActionListener {

    JFrame frame = new JFrame();
    String[] options = {CoinName.UTNCOIN.name(),CoinName.BITCOIN.name(),CoinName.ETHEREUM.name(),CoinName.LUNA.name()};

    JComboBox<String> jComboBox = new JComboBox<>(options);
    JLabel text = new JLabel();
    JButton back = new JButton();
    JButton search = new JButton("Seleccionar");
    JButton confirm = new JButton("Modificar");
    JTextField textBox = new JTextField();

    String userConnected;

    public AdminChangeValue(String userID) {
        this.userConnected = userID;
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);

        back.setBounds(0,0, 50,25);
        jComboBox.setBounds(80, 50, 200, 20);
        search.setBounds(100, 100, 120, 20);
        textBox.setBounds(100,190,120,25);
        text.setBounds(100,150,300,20);
        confirm.setBounds(100, 240, 120, 20);

        text.setFont(new Font(null, Font.ITALIC,15));
        text.setText("Ingrese nuevo valor:");

        search.addActionListener(this);
        back.addActionListener(this);
        confirm.addActionListener(this);

        frame.add(search);
        frame.add(back);
        frame.add(jComboBox);
        frame.add(textBox);
        frame.add(text);
        frame.add(confirm);

        text.setVisible(false);
        textBox.setVisible(false);
        confirm.setVisible(false);

        search.setFocusable(false);
        confirm.setFocusable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(516,538);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        User user = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
        if (e.getSource() == back){
            frame.dispose();
            user.obtenerMenu().show();
        }
        if (e.getSource() == search){
            text.setVisible(true);
            textBox.setVisible(true);
            confirm.setVisible(true);
        }
        if (e.getSource() == confirm){

            if(Double.parseDouble(textBox.getText())!=user.getWallet().searchCoinByName(jComboBox.getItemAt(jComboBox.getSelectedIndex())).getValueUSD())
            {
                if(Double.parseDouble(textBox.getText())>0)
                {
                    JsonUser.changeUSDvalueOfCoin(jComboBox.getItemAt(jComboBox.getSelectedIndex()), Double.parseDouble(textBox.getText()));
                    JOptionPane.showMessageDialog(null,"El Valor de la Moneda Se Cambió Exitosamente a: U$D " + textBox.getText());
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"El Valor de la Moneda No Puede Ser 0.");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"El Valor Ingresado Es El Actual.");
            }
        }
    }
}