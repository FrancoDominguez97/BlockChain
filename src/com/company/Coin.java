package com.company;

import com.company.enums.CoinName;

public class Coin {

    private CoinName coinName;
    private String coinSymbol; //3 ó 4 caracteres que representan la moneda por ej: BTC para bitcoin.
    //private icon
    private double amount;
    private String description;

    public Coin() {

    }

    public Coin(CoinName coinName, String coinSymbol, double amount, String description) {
        this.coinName = coinName;
        this.coinSymbol = coinSymbol;
        this.amount = amount;
        this.description = description;
    }

    public CoinName getCoinName() {
        return coinName;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "coinName='" + coinName + '\'' +
                ", coinSymbol='" + coinSymbol + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
