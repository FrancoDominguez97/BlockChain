package com.company.Transferencias;

import com.company.enums.CoinName;
import com.company.enums.Status;
import com.company.enums.Reason;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonProperty()
public class Transaction {

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private String id;
    private String sender;
    private String receiver;
    private String dateTime;
    private String[]userValidate = new String[3];
    private double amount;
    private CoinName coinName;
    private Status status;
    private Reason reason;
    public Transaction(String sender, String receiver, double amount, CoinName coinName, Status status, Reason reason) {
        this.id = UUID.randomUUID().toString();
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.coinName = coinName;
        this.status = status;
        this.reason = reason;
        this.dateTime = fmt.format(LocalDateTime.now());
    }



    public Transaction() {
        this.id = UUID.randomUUID().toString();
        this.dateTime = fmt.format(LocalDateTime.now());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id= id;
    }
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String[] getUserValidate() {
        return userValidate;
    }

    public void setUserValidate(String[] userValidate) {
        this.userValidate = userValidate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CoinName getCoinName() {
        return coinName;
    }

    public void setCoinName(CoinName coinName) {
        this.coinName = coinName;
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
    public boolean recorrerArreglo(String wallet){
        int i = 0;
        while (i<userValidate.length) {
            if (userValidate[i] == wallet){
                return true;
            }
            else{
                i++;
            }
        }
        return false;
    }
    public boolean arregloLleno(){
        int i = 0;
        while (i<userValidate.length){
            if(userValidate[i] != null)
                i++;
        }
        if (i<3){
        return false;
        }
        return true;

    }
    public void addUserValidate(String walletUser){
        int i = 0;
        while (userValidate[i]!=null && i<userValidate.length) {
            i++;
        }
        if (i<userValidate.length) {
            userValidate[i] = walletUser;
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", senderId=" + sender +
                ", receiverId=" + receiver +
                ", dateTime=" + dateTime +
                ", status=" + status +
                ", reason=" + reason +
                '}';
    }



    // logo en 2D para tiempo de espera de aprobacion de transaccion.
}
