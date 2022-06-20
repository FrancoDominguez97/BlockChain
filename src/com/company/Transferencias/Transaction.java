package com.company.Transferencias;

import com.company.JSON.JsonManager;
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
    //Aca en los get de RECEIVERID y SENDERID deberia devolver el usuario tipo los datos principales del usuario.

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
        return "Transaction{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", dateTime=" + dateTime +
                ", coin=" + coin +
                ", userValidations=" + userValidations +
                ", status=" + status +
                ", reason=" + reason +
                '}';
    }

    public boolean checkValidated(String userID)
    {
        //User user = JsonManager.searchUserByIdWallet(JsonManager.JSON_USERS,userID);

        for(String validationUser : userValidations)
        {
            if(validationUser.equals(userID))
            {
                return true;
            }
        }
        return false;
    }

    public int validate(String userID)
    {
        if(userValidations.size()<3)
        {
            userValidations.add(userID);
            if(userValidations.size()==3)
            {
                // remover de lista pendiente, sumar a aceptada, y SUMAR MONTO al receptor
                User receiver = JsonManager.searchUserByIdWallet(JsonManager.JSON_USERS,this.receiverId);

                receiver.getWallet().searchCoinByName(this.getCoin().getCoinName().name()).setAmount(receiver.getWallet().searchCoinByName(this.getCoin().getCoinName().name()).getAmount() + this.coin.getAmount());
                JsonManager.updateUser(receiver);

                this.moveToBlockchain();
                return 1;
            }
        }
        return 0;
    }

    public void moveToBlockchain()
    {
        List<Transaction> pendingList = JsonManager.readJsonTransfer(JsonManager.JSON_PENDING_TRANSACTIONS);
        List<Transaction> acceptedList = JsonManager.readJsonTransfer(JsonManager.JSON_BLOCKCHAIN);

        for(Transaction t : pendingList)
        {
            if(t.getId().equals(this.id))
            {
                this.status = Status.ACCEPTED;
                pendingList.remove(t);
                acceptedList.add(t);
                JsonManager.writeToJson(JsonManager.JSON_BLOCKCHAIN,acceptedList);
                JsonManager.writeToJson(JsonManager.JSON_PENDING_TRANSACTIONS,pendingList);
            }
        }
    }


    // logo en 2D para tiempo de espera de aprobacion de transaccion.
}
