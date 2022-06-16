package com.company;

import com.company.Visuals.Registro;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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

    private List<Transaction> transactionList = new ArrayList<>();
    private List<Wallet> walletList = new ArrayList<>();

    JFrame frame = new JFrame();

    JLabel nameVisual = new JLabel("Nombre");
    JTextField nameField = new JTextField();
    JLabel lastNameVisual = new JLabel("Apellido");
    JTextField lastNameField = new JTextField();
    JLabel userNameVisual = new JLabel("Username");
    JTextField userNameField = new JTextField();
    JLabel passVisual = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField();
    JLabel dateVisual = new JLabel("FechaNacimiento");
    JTextField dateField = new JTextField();
    JLabel emailVisual = new JLabel("Email");
    JTextField emailField = new JTextField();

    JButton guardar = new JButton();
    JButton limpiar = new JButton();

    public User() { }
    public User(String userName, String name, String lastName, String dateOfBirth, String email, String password) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }
    public String getWalletId() { return walletId.toString(); }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName;}
    public String getDateOfBirth() { return dateOfBirth;}
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
    public List<Transaction> getTransactionList() { return transactionList; }
    public List<Wallet> getWalletList() {
        return walletList;
    }

    public void register() {
        Registro registro = new Registro();
    }

    @Override
    public String toString() {
        return "User{" +
                "walletId=" + walletId +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", transactionList=" + transactionList +
                ", walletList=" + walletList +
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
        String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

}

