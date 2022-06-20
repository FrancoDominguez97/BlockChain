package com.company.Usuarios;

import com.company.enums.CoinName;

import java.text.DecimalFormat;

public class Coin {

    private CoinName coinName;
    //private icon
    private  double amount;
    private  double valueUSD;

    public Coin() {

    }

    public Coin(CoinName coinName, double amount)
    {
        this.coinName = coinName;
        this.amount = amount;
    }

    public Coin(CoinName coinName, double amount, double valueUSD) {
        this.coinName = coinName;
        this.amount = amount;
        this.valueUSD = valueUSD;
    }

    public CoinName getCoinName() {
        return coinName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getValueUSD() {
        return valueUSD;
    }

    public void setValueUSD(double valueUSD) {
        this.valueUSD = valueUSD;
    }

    @Override
    public String toString() {
        return new DecimalFormat("#.00000").format(amount) + " " + coinName;
    }

    public double calculateValueInUSD()
    {
        return amount*valueUSD;
    }
}
