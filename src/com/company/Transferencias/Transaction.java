package com.company.Transferencias;

import com.company.enums.Status;
import com.company.enums.Reason;
import com.company.Usuarios.Coin;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private UUID id = UUID.randomUUID();
    private UUID senderId;
    private UUID receiverId;
    private LocalDateTime dateTime;
    private Coin coin;
    private int validationCounter = 0;
    private Status status;
    private Reason reason;

    public Transaction(UUID senderId, UUID receiverId, LocalDateTime dateTime, Coin coin, Status status, Reason reason) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.dateTime = dateTime;
        this.coin = coin;
        this.status = status;
        this.reason = reason;
    }

    public Transaction() {
    }

    public UUID getId() {
        return id;
    }
    //Aca en los get de RECEIVERID y SENDERID deberia devolver el usuario tipo los datos principales del usuario.

    public UUID getSenderId() {
        return senderId;
    }
    public UUID getReceiverId() {
        return receiverId;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public Coin getCoin() {
        return coin;
    }
    public int getValidationCounter() {
        return validationCounter;
    }

    public void setValidationCounter(int validationCounter) {
        this.validationCounter = validationCounter;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", dateTime=" + dateTime +
                ", coin=" + coin +
                ", validationCounter=" + validationCounter +
                ", status=" + status +
                ", reason=" + reason +
                '}';
    }
    // logo en 2D para tiempo de espera de aprobacion de transaccion.
}
