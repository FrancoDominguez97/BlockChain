package com.company.Usuarios;


import com.company.Transferencias.Transaction;
import java.util.ArrayList;
import java.util.List;
public class Wallet {
    private Coin coin;
    private List<Transaction> transferList = new ArrayList<>();

    public Wallet() {

    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public List<Transaction> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<Transaction> transferList) {
        this.transferList = transferList;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "Coin="+coin +
                "Transaction=" + transferList +
                '}';
    }
}

