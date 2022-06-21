package com.company.Transferencias;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonTransaction;
import com.company.JSON.JsonUser;
import com.company.Usuarios.User;
import com.company.enums.Status;
import com.company.enums.Reason;
import com.company.Usuarios.Coin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transaction {

    private final UUID id = UUID.randomUUID();
    private String senderId;
    private String receiverId;
    private final String dateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now());
    private Coin coin;
    private List<String> userValidations = new ArrayList<>(); //Lista de walletID que validaron la transaccion
    private Status status;
    private Reason reason;

    public Transaction(String senderId, String receiverId, Coin coin, Reason reason) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.coin = coin;
        this.status = Status.PENDING;
        this.reason = reason;
    }

    public Transaction() {
        this.status = Status.PENDING;
    }

    public UUID getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }
    public String getReceiverId() {
        return receiverId;
    }
    public String getDateTime() {
        return dateTime;
    }
    public Coin getCoin() {
        return coin;
    }

    public List<String> getUserValidations() {
        return userValidations;
    }

    public void setUserValidations(List<String> userValidations) {
        this.userValidations = userValidations;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    @Override
    public String toString() {
        return "\nID: " + id + "\n" +
                "Sender: " + senderId + '\n' +
                "Receiver: " + receiverId + "\n" +
                "Fecha: " + dateTime + "\n" +
                "Coin: " + coin + "\n" +
                "Estado: " + status + "\n" +
                "Razon: " + reason + "\n";
    }

    public boolean checkValidated(String userID)
    {
        if(!senderId.equals(userID) && !receiverId.equals(userID))
        {
            for(String validationUser : userValidations)
            {
                if(validationUser.equals(userID))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int validate(String userID)
    {
        if(userValidations.size()<3)
        {
            userValidations.add(userID);
            JsonTransaction.updateTransaction(this);
            User emitter = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,this.senderId);
            emitter.getWallet().updateTransactionInList(this);

            JsonUser.updateUser(emitter);
            if(userValidations.size()==3)
            {
                User receiver = JsonUser.searchUserByIdWallet(JsonManager.JSON_USERS,this.receiverId);

                receiver.getWallet().searchCoinByName(this.getCoin().getCoinName().name()).setAmount(receiver.getWallet().searchCoinByName(this.getCoin().getCoinName().name()).getAmount() + this.coin.getAmount());

                this.moveToBlockchain(); //Lo saca de pending, lo manda a blockchain y cambia el status a ACCEPTED.
                this.setStatus(Status.ACCEPTED);
                receiver.getWallet().getTransferList().add(this);
                emitter.getWallet().updateTransactionInList(this);

                JsonUser.updateUser(receiver);
                JsonUser.updateUser(emitter);
                return 1;
            }
        }
        return 0;
    }


    public void moveToBlockchain()
    {
        List<Transaction> pendingList = JsonTransaction.readJsonTransfer(JsonManager.JSON_PENDING_TRANSACTIONS);
        List<Transaction> acceptedList = JsonTransaction.readJsonTransfer(JsonManager.JSON_BLOCKCHAIN);
        Transaction aux = null;

        for(Transaction t : pendingList)
        {
            if(t.getId().equals(this.id))
            {
                aux = t;
            }
        }
        if (aux!=null)
        {
            pendingList.remove(aux);
            acceptedList.add(aux);
            aux.setStatus(Status.ACCEPTED);

            JsonManager.writeToJson(JsonManager.JSON_PENDING_TRANSACTIONS,pendingList);
            JsonManager.writeToJson(JsonManager.JSON_BLOCKCHAIN,acceptedList);
        }
    }
}
