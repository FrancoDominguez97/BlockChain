package com.company.Usuarios;

import com.company.JSON.JsonManager;
import com.company.Visuals.Menu;
import com.company.Visuals.ProgramAdmin;
import com.company.Visuals.ProgramUser;
import com.company.Visuals.Registro;
import com.company.enums.CoinName;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private UUID walletId = UUID.randomUUID();
    private String userName;
    private String name;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String password;
    private Wallet wallet = new Wallet();
    private double fee;
    private boolean admin;

    public User() {
    }

    public User(String userName, String name, String lastName, String dateOfBirth, String email, String password) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.fee = 0.02;
        this.admin = false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public String getWalletId() {
        return walletId.toString();
    }

    public void register() {
        Registro registro = new Registro();
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Menu obtenerMenu()
    {
        if (this.admin)
        {
            return new ProgramAdmin(walletId.toString());
        }else
        {
            return new ProgramUser(walletId.toString());
        }
    }

    //Método para swappear de una moneda a otra, retorna True si fue posible. Consume un fee de la coin a sacar montos.
    public boolean swapCoin(String coinFrom, String coinTo, double amount){
        Coin from = wallet.searchCoinByName(coinFrom);
        Coin to = wallet.searchCoinByName(coinTo);
        boolean possible = false;

        if(to!=null && from!=null && amount>0)
        {
            if(from.getAmount()>=(amount+fee))
            {
                int indexFrom = wallet.getCoins().indexOf(from);
                int indexTo = wallet.getCoins().indexOf(to);
                from.setAmount(from.getAmount()-(amount+fee));
                to.setAmount(to.getAmount()+amount);
                wallet.getCoins().set(indexFrom,from);
                wallet.getCoins().set(indexTo,to);

                JsonManager.updateUser(this);
                possible=true;
            }
        }
        return possible;
    }


    @Override
    public String toString() {
        return "User{" +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", wallet='" + wallet + '\'' +
                '}';
    }

    public boolean calcutaleAge(String ageBorn) {
        boolean b = true;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate yearOfBorn = LocalDate.parse(ageBorn, fmt);
        Period age = Period.between(yearOfBorn, LocalDate.now());
        if (age.getYears() < 18) {
            b = false;
            return b;
        }
        return b;
    }

    public boolean emailVerify(String mail) {
        String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z.-]+$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    public Coin getCoin(String coinName){
        for (Coin coinFound: wallet.getCoins()) {
            if(coinFound.getCoinName().equals(CoinName.valueOf(coinName)))
                return coinFound;
        }
        return null;
    }
}


