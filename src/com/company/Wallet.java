package com.company;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private List<Coin> coinList = new ArrayList();

    public List<Coin> getCoins() {
        return coinList;
    }

    public void setCoins(List<Coin> coins) {
        coinList = coins;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "Coins=" + coinList +
                '}';
    }

    public void addCoinToWallet(Coin coin){
        coinList.add(coin);
    }
}
