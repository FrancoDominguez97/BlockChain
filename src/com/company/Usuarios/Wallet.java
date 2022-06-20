package com.company.Usuarios;

import com.company.JSON.JsonManager;
import com.company.JSON.JsonTransaction;
import com.company.JSON.JsonUser;
import com.company.Transferencias.Transaction;
import com.company.enums.CoinName;
import com.company.enums.Reason;
import com.company.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Wallet {
    public static final String JSON_PENDING_TRANSACTIONS = "pending_transactions.json"; //Archivo con registros de transacciones pendientes (todavía no fueron validadas por completo)

    private List<Coin> coinList = new ArrayList<>();
    private List<Transaction>transferList = new ArrayList<>();

    public Wallet(){ //Vacío, antes creaba 100 UTNcoins y las añadía a la coinList. Eso traía duplicados al leer el json con ObjectMapper
    }

    public List<Coin> getCoins() {
        return coinList;
    }

    public List<Transaction> getTransferList() {
        return transferList;
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }

    public void setTransferList(List<Transaction> transferList) {
        this.transferList = transferList;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "Coins=" + coinList +
                "Transaction=" + transferList +
                '}';
    }

    public void initialize()
    {
        Coin coin = new Coin(CoinName.UTNCOIN,"UTN",100,"La mejor moneda de Mar del Plata",1.0);
        addCoinToWallet(coin);
    }

    // método que calcula toodo el valor en dólares que contiene la wallet
    public double totalUSDvalue()
    {
        double total = 0.0;
        for (Coin coin : coinList)
        {
            total+= coin.calculateValueInUSD();
        }
        return total;
    }

    public void addCoinToWallet(Coin coin){
        for(Coin c : coinList)
        {
            if(c.getCoinName().equals(coin.getCoinName()))
            {
                return;
            }
        }
        coinList.add(coin);
    }

    public Coin searchCoinByName(String coin)
    {
        for(Coin c : coinList)
        {
            if(c.getCoinName().name().equals(coin))
                return c;
        }
        return null;
    }

    //Método que checkea si hay suficientes fondos de una coin especifica en la wallet
    public boolean validateAmount(double amount,String coinName)
    {
        Coin coin = this.searchCoinByName(coinName);
        if (coin!=null && coin.getAmount()>=amount)
        {
            return true;
        }
        else
            return false;
    }

/*    public boolean swapCoin(String coinFrom, String coinTo, double amount){
        // Habría que checkear que amount sea positivo distinto de 0 en el parámetro
        Coin from = searchCoinByName(coinFrom);
        Coin to = searchCoinByName(coinTo);
        boolean possible = false;

        if(to!=null && from!=null && amount>0)
        {
            if(from.getAmount()>=amount)
            {
                int indexFrom = coinList.indexOf(from);
                int indexTo = coinList.indexOf(to);
                from.setAmount(from.getAmount()-(amount+fee));
                to.setAmount(to.getAmount()+amount);
                coinList.set(indexFrom,from);
                coinList.set(indexTo,to);

                JsonManager.
            }
        }

        return possible;
    }*/

    public void updateTransactionInList(Transaction transaction)
    {
        // funciona igual que un foreach
        List<Transaction> transactionUpdated = this.transferList.stream()
                .map(t -> {
                    if (t.getId().toString().equals(transaction.getId().toString()))
                        t = transaction;
                    return t;
                })
                .collect(Collectors.toList());
        this.setTransferList(transactionUpdated);
    }




}
