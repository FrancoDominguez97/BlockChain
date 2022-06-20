package com.company.Visuals;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonUser;
import com.company.Transferencias.Transaction;
import com.company.Usuarios.Coin;
import com.company.Usuarios.User;
import com.company.enums.CoinName;
import com.company.enums.Reason;
import com.company.enums.TransactionToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TransactionVisual implements ActionListener {

    JFrame frame = new JFrame();
    String[] options = {TransactionToDo.SELECCIONE.name(), TransactionToDo.NUEVA_TRANSFERENCIA.name(),TransactionToDo.HISTORIAL_TRANSFERENCIAS.name()};
    String[] typeOfCoin = {CoinName.UTNCOIN.name(),CoinName.BITCOIN.name(),CoinName.ETHEREUM.name(),CoinName.LUNA.name()};
    String[] typeOfReason = {Reason.OTHERS.name(),Reason.FEE.name(),Reason.RENT.name(),Reason.SALARY.name()};

    JComboBox<String> jComboBox = new JComboBox<>(options);
    JButton search = new JButton("Buscar");
    JLabel jLabel = new JLabel();
    JButton back = new JButton();
    JButton send = new JButton("Enviar");
    JLabel walletIdentificator = new JLabel();

    //datos de transaccion creada
    JLabel nameVisual = new JLabel();
    JTextField nameField = new JTextField();
    JLabel amountVisual = new JLabel();
    JTextField amountField = new JTextField();
    JLabel coinVisual = new JLabel();
    JComboBox coinBox = new JComboBox<>(typeOfCoin);
    JLabel reasonVisual = new JLabel();
    JComboBox reasonBox = new JComboBox<>(typeOfReason);

    JFrame window = new JFrame("Historial de transacciones:");
    JLabel historyOfTransactions = new JLabel();
    JTextPane text = new JTextPane();
    JScrollBar vertical = new JScrollBar();
    JScrollPane scrollbar = new JScrollPane();

    String userConnected;
    public TransactionVisual(String userID) {
        this.userConnected = userID;
        frame.setTitle("TP FINAL LABORATORIO 3");
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        window.setIconImage(image.getImage());

        ImageIcon backImage = new ImageIcon("flecha.png");
        back.setIcon(backImage);

        jComboBox.setBounds(80, 50, 250, 25);
        back.setBounds(0,0, 50,25);
        search.setBounds(100, 100, 90, 20);

        send.setBounds(300, 400, 90, 20);

        walletIdentificator.setBounds(70,20,400,25);
        walletIdentificator.setText("Wallet ID: " + userID);
        walletIdentificator.setFont((new Font(null,Font.PLAIN,15)));


        nameVisual.setBounds(40, 140, 400, 25);
        nameField.setBounds(160, 140, 200, 25);
        nameVisual.setText("Wallet a Transferir:");

        coinVisual.setBounds(40, 180, 400, 25);
        coinBox.setBounds(160, 180, 150, 25);
        coinVisual.setText("Tipo de coin:");

        amountVisual.setBounds(40, 220, 400, 25);
        amountField.setBounds(160, 220, 40, 25);
        amountVisual.setText("Cantidad:");

        reasonVisual.setBounds(40, 260, 400, 25);
        reasonBox.setBounds(160, 260, 150, 25);
        reasonVisual.setText("Motivo:");

        frame.add(back);
        frame.add(jComboBox);
        frame.add(search);
        frame.add(walletIdentificator);
        frame.add(jLabel);
        frame.add(send);

        frame.add(nameVisual);
        frame.add(nameField);
        frame.add(coinBox);
        frame.add(coinVisual);
        frame.add(reasonBox);
        frame.add(reasonVisual);
        frame.add(amountField);
        frame.add(amountVisual);

        nameField.setVisible(false);
        nameVisual.setVisible(false);

        amountField.setVisible(false);
        amountVisual.setVisible(false);

        coinBox.setVisible(false);
        coinVisual.setVisible(false);

        reasonBox.setVisible(false);
        reasonVisual.setVisible(false);

        send.setVisible(false);

        search.addActionListener(this);
        back.addActionListener(this);
        send.addActionListener(this);

        back.setFocusable(false);
        search.setFocusable(false);
        send.setFocusable(false);

        historyOfTransactions.setBounds(30,30,400,200);
        historyOfTransactions.setVisible(false);
        text.setBounds(10,20,400,800);
        text.setVisible(false);
        text.setEditable(false);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.add(historyOfTransactions);
        window.setBounds(10,160,440,280);
        window.setLayout(null);
        window.setVisible(false);

        window.add(text);
        window.getContentPane().setLayout(new BorderLayout());
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollbar.setVerticalScrollBar(vertical);
        scrollbar.setViewportView(text);
        window.getContentPane().add(scrollbar);
        window.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User user = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
        if (e.getSource() == search){
            if(jComboBox.getItemAt(jComboBox.getSelectedIndex()).equals(TransactionToDo.NUEVA_TRANSFERENCIA.name())){
                //NUEVA TRANSACCION
                jLabel.setText("Nueva transaccion:");
                jLabel.setVisible(true);

                nameField.setVisible(true);
                nameVisual.setVisible(true);
                coinBox.setVisible(true);
                coinVisual.setVisible(true);
                amountField.setVisible(true);
                amountVisual.setVisible(true);
                reasonBox.setVisible(true);
                reasonVisual.setVisible(true);
                send.setVisible(true);

                System.out.println("apreto search");


            }

            else if(jComboBox.getItemAt(jComboBox.getSelectedIndex()).equals(TransactionToDo.HISTORIAL_TRANSFERENCIAS.name())){
                //HISTORIAL DE TRANSACCIONES
                jLabel.setText("Historial de transacciones:");
                jLabel.setVisible(true);
                nameField.setVisible(false);
                nameVisual.setVisible(false);
                coinBox.setVisible(false);
                coinVisual.setVisible(false);
                amountField.setVisible(false);
                amountVisual.setVisible(false);
                reasonBox.setVisible(false);
                reasonVisual.setVisible(false);
                send.setVisible(false);

                window.setVisible(true);
                historyOfTransactions.setVisible(true);
                scrollbar.setVisible(true);
                vertical.setVisible(true);
                text.setText(user.getWallet().getTransferList().toString());
                text.setVisible(true);


            }
            else{
                jLabel.setVisible(false);
                nameField.setVisible(false);
                nameVisual.setVisible(false);
                coinBox.setVisible(false);
                coinVisual.setVisible(false);
                amountField.setVisible(false);
                amountVisual.setVisible(false);
                reasonBox.setVisible(false);
                reasonVisual.setVisible(false);
                send.setVisible(false);
            }
        }

        if (e.getSource() == send) {

            System.out.println("comienzo send");
            //validar lo q hay que mandar
            User userReceiver = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,nameField.getText());
            Transaction newTransaction = new Transaction();
            newTransaction.setSenderId(user.getWalletId());
            newTransaction.setReason(Reason.valueOf(Objects.requireNonNull(reasonBox.getSelectedItem()).toString()));
            double amount = Double.parseDouble(amountField.getText());
            CoinName coinName = CoinName.valueOf(Objects.requireNonNull(coinBox.getSelectedItem()).toString());

            if(!(user.getWalletId().equals(nameField.getText())))
            {
                if(userReceiver!=null)
                {

                    newTransaction.setReceiverId(userReceiver.getWalletId());

                    if(user.getWallet().validateAmount(amount + amount*user.getFee(),coinName.name()))
                    {
                        Coin newCoin = new Coin(coinName,amount);
                        newTransaction.setCoin(newCoin);
                        System.out.println(newTransaction);
                        user.transfer(newTransaction);
                        JOptionPane.showMessageDialog(null, "Transaccion Exitosa! El Monto ha Sido Descontado, Esperando Validaciones.");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Monto Insuficiente En Wallet.");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No Se Encontro la Wallet.");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No Puede Transferirse a Si Mismo!");
            }

            System.out.println("final");


            // Mostrar en visual cuánto es la fee que se le cobraría al usuario.

            //transferencias.setNameField(nameField.getText());
            //user.getCoin((coinBox.getName()))
            //Transaction.getReason((reasonBox.getName()))
            // y asi con todos
            //JsonManager.searchUserByIdWallet(JsonManager.JSON_USERS,"06255cf0-b915-4de6-94dd-126d84c91d33");

            //if(coinBox.getItemAt(coinBox.getSelectedIndex()).equals(user.getCoin((coinBox.getName())))){
            //}
        }

        if (e.getSource() == back){
            frame.dispose();
            //User user = JsonManager.searchUserByIdWallet(JsonManager.JSON_USERS,userConnected);
            user.obtenerMenu().show();
        }
    }
}