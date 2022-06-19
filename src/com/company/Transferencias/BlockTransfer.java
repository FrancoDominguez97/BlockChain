package com.company.Transferencias;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonTransfer;
import com.company.JSON.JsonUser;
import com.company.Usuarios.Coin;
import com.company.Usuarios.User;
import com.company.Usuarios.Wallet;
import com.company.enums.CoinName;
import com.company.enums.Reason;
import com.company.enums.Status;


import javax.sound.midi.Soundbank;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;

public class BlockTransfer {
    public static void blockChain() {
        List<Transaction> pendientes = JsonTransfer.JsonToListTransfer(JsonManager.JSON_PENDING_TRANSACTIONS);
        for (Transaction validar : pendientes) {
            if (validar!=null){
                System.out.println("hola");
                validar(validar);
            }
        }
    }

    public static void send(User userSender) {
        Scanner scan = new Scanner(System.in);
        Transaction send = new Transaction();
        send.setSender(userSender.getWalletId());
        System.out.println("Usuario a buscar: ");
        User userFound = JsonUser.searchUserByUserName(JsonManager.JSON_USERS, scan.next());
        while (userFound == null) {
            System.out.println("Usuario no encontrado");
            userFound = JsonUser.searchUserByUserName(JsonManager.JSON_USERS, scan.next());
        }
        send.setReceiver(userFound.getWalletId());
        System.out.println("Monto a enviar: ");
        double amountToSend = scan.nextInt();
        while (userSender.getWallet().getCoin().getAmount() < amountToSend) {
            System.out.println("Monto insuficiente");
            amountToSend = scan.nextInt();
        }
        send.setCoinName(userSender.getWallet().getCoin().getCoinName());
        send.setAmount(amountToSend);
        send.setReason(Reason.OTHERS);
        send.setStatus(Status.PENDING);
        System.out.println("hola");
        JsonTransfer.agregarPendiente(send);
    }

    public static void validar(Transaction aValidar) {
        User userRan = JsonUser.returnUserRand();
        if (!userRan.getWalletId().equals(aValidar.getSender()) && !userRan.getWalletId().equals(aValidar.getReceiver())) {
            if (!aValidar.recorrerArreglo(userRan.getWalletId()) && !aValidar.arregloLleno() ) {
                System.out.println("hola1");
                aValidar.addUserValidate(userRan.getWalletId());
                    List<Transaction> list = JsonTransfer.JsonToListTransfer(JsonManager.JSON_PENDING_TRANSACTIONS);
                    int pos = JsonTransfer.returnPos(aValidar.getId());
                    System.out.println("HOLA");
                    list.set(pos, aValidar);
                    JsonTransfer.writeToJson(JsonManager.JSON_PENDING_TRANSACTIONS,list);
                }
                else {
                    aValidar.setStatus(Status.ACCEPTED);
                    List<Transaction> aceptadas = JsonTransfer.JsonToListTransfer(JsonManager.JSON_BLOCKCHAIN);
                    List<Transaction> list = JsonTransfer.JsonToListTransfer(JsonManager.JSON_PENDING_TRANSACTIONS);
                    int pos = JsonTransfer.returnPos(aValidar.getId());
                    list.remove(pos);
                    aceptadas.add(aValidar);
                    JsonTransfer.writeToJson(JsonManager.JSON_PENDING_TRANSACTIONS,list);
                    JsonManager.writeToJson(JsonManager.JSON_BLOCKCHAIN, aceptadas);
                }
            }
        }
    }