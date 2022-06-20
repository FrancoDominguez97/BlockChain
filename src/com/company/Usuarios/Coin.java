package com.company.Usuarios;

import com.company.enums.CoinName;

public class Coin {

    private CoinName coinName;
    private String coinSymbol; //3 ó 4 caracteres que representan la moneda por ej: BTC para bitcoin.
    //private icon
    private double amount;
    private String description;
    private double valueUSD;

    public Coin() {

    }

    public Coin(CoinName coinName, double amount)
    {
        this.coinName = coinName;
        this.amount = amount;
    }

    public Coin(CoinName coinName, String coinSymbol, double amount, String description, double valueUSD) {
        this.coinName = coinName;
        this.coinSymbol = coinSymbol;
        this.amount = amount;
        this.description = description;
        this.valueUSD = valueUSD;
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

    public double getValueUSD() {
        return valueUSD;
    }

    public void setValueUSD(double valueUSD) {
        this.valueUSD = valueUSD;
    }

    @Override
    public String toString() {
        return amount + " " + coinName;
    }

    public double calculateValueInUSD()
    {
        return amount*valueUSD;
    }
}
