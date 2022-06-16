package com.company;

import com.company.Transferencias.Transaction;
import com.company.Usuarios.Coin;
import com.company.enums.Reason;
import com.company.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Wallet {
    public static final String JSON_PENDING_TRANSACTIONS = "pending_transactions.json"; //Archivo con registros de transacciones pendientes (todavía no fueron validadas por completo)

    private List<Coin> coinList = new ArrayList<>();
    private List<Transaction>transferList = new ArrayList<>();

    public Wallet(){
    }

    public List<Coin> getCoins() {
        return coinList;
    }

    public List<Transaction> getTransferList() {
        return transferList;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "Coins=" + coinList +
                "Transaction=" + transferList +
                '}';
    }

    public void addCoinToWallet(Coin coin){
        coinList.add(coin);
    }
    public Transaction sendCoin(UUID sender, UUID receiver, Coin coin, double amount, Reason reason){
        LocalDateTime dateTransfer = LocalDateTime.now();
        for (Coin sendCoin: coinList) {
            if(sendCoin.getCoinName() == coin.getCoinName()){
                if(sendCoin.getAmount() >= amount){
                    Transaction transfer = new Transaction(sender, receiver, dateTransfer,coin,Status.PENDING,reason);
                    ///ACA HAY QUE HACER UNA FUNCION DONDE SE HAGA LA VALIDACION DE LA TRANSFERENCIA;
                    transferList.add(transfer);
                    addTransferPendinJson(transfer);
                    return transfer;
                }
            }
        }
        return null;
    }
    //ESTA FUNCION SE PUEDE REFACTORIZAR SI ENCONTRAMOS UNA MEJOR MANERA
    public void addTransferPendinJson(Transaction transaction){
        JsonManager json = new JsonManager();
        List<Transaction> pendingList = json.readJsonPendingTransfer(JSON_PENDING_TRANSACTIONS);
        pendingList.add(transaction);
        json.writeToJson(JSON_PENDING_TRANSACTIONS,pendingList);
    }
}
