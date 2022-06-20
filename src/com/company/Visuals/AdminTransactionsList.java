package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonTransaction;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;
import com.company.enums.TransactionToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminTransactionsList implements ActionListener {
    JFrame frame = new JFrame();
    JButton search = new JButton("Buscar");

    String[] options = {TransactionToDo.SELECCIONE.name(), TransactionToDo.PENDING_TRANSFERS.name(), TransactionToDo.VER_BLOCKCHAIN.name()};
    JComboBox<String> jComboBox = new JComboBox<>(options);

    JFrame windowBlockChain = new JFrame("BlockChain");
    JLabel blockChain = new JLabel();
    JTextPane textBlockChain = new JTextPane();
    JScrollBar verticalBlockChain = new JScrollBar();
    JScrollPane scrollbarBlockChain = new JScrollPane();

    JButton back = new JButton();

    String userConnected;
    public AdminTransactionsList(String userID) {
        this.userConnected = userID;
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);

        windowBlockChain.setIconImage(image.getImage());

        back.setBounds(0,0, 50,25);
        jComboBox.setBounds(80, 50, 200, 20);
        search.setBounds(100, 100, 90, 20);

        search.addActionListener(this);
        back.addActionListener(this);

        frame.add(back);
        frame.add(jComboBox);
        frame.add(search);
        frame.add(blockChain);
        frame.add(scrollbarBlockChain);
        frame.add(verticalBlockChain);
        frame.add(textBlockChain);


        blockChain.setBounds(30,30,400,200);
        blockChain.setVisible(false);
        textBlockChain.setBounds(10,20,400,800);
        textBlockChain.setVisible(false);
        textBlockChain.setEditable(false);
        windowBlockChain.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        windowBlockChain.add(blockChain);
        windowBlockChain.setBounds(10,160,440,280);
        windowBlockChain.setLayout(null);
        windowBlockChain.setVisible(false);

        windowBlockChain.add(textBlockChain);
        windowBlockChain.getContentPane().setLayout(new BorderLayout());
        scrollbarBlockChain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollbarBlockChain.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollbarBlockChain.setVerticalScrollBar(verticalBlockChain);
        scrollbarBlockChain.setViewportView(textBlockChain);
        windowBlockChain.getContentPane().add(scrollbarBlockChain);

        windowBlockChain.setResizable(false);
        blockChain.setVisible(false);
        windowBlockChain.setVisible(false);
        scrollbarBlockChain.setVisible(false);
        verticalBlockChain.setVisible(false);
        textBlockChain.setVisible(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
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
        if(e.getSource() == search){
            if(jComboBox.getItemAt(jComboBox.getSelectedIndex()).equals(TransactionToDo.PENDING_TRANSFERS.name())){
                blockChain.setVisible(true);
                windowBlockChain.setVisible(true);
                scrollbarBlockChain.setVisible(true);
                verticalBlockChain.setVisible(true);
                textBlockChain.setText(JsonTransaction.printPendings().toString());
                textBlockChain.setVisible(true);
                }
            else if(jComboBox.getItemAt(jComboBox.getSelectedIndex()).equals(TransactionToDo.VER_BLOCKCHAIN.name())){
                blockChain.setVisible(true);
                windowBlockChain.setVisible(true);
                scrollbarBlockChain.setVisible(true);
                verticalBlockChain.setVisible(true);
                textBlockChain.setText(JsonTransaction.printBlockChain().toString());
                textBlockChain.setVisible(true);
            }
            else{
                blockChain.setVisible(false);
                windowBlockChain.setVisible(false);
                scrollbarBlockChain.setVisible(false);
                verticalBlockChain.setVisible(false);
                textBlockChain.setVisible(false);
            }
        }
    }
}
