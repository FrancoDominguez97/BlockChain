package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    public User() { }
    public User(String userName, String name, String lastName, String dateOfBirth, String email, String password) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }
    public UUID getWalletId() { return walletId; }
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
        Scanner read = new Scanner(System.in);
        System.out.println("Nombre: ");
        this.name = read.next();
        System.out.println("Apellido: ");
        this.lastName = read.next();
        System.out.println("Fecha de nacimiento: ");
        this.dateOfBirth = read.next();
        if (calcutaleAge(dateOfBirth) == false) {
            System.out.println("Usted es menor de edad no se puede regristrar");
            System.exit(1);
        }
        System.out.println("Nombre de usuario: ");
        //Aca realizar una funcion para que busque si no esta en uso el user name.
        this.userName = read.next();
        //searchUserName(userName);
        System.out.println("Email: ");
        this.email = read.next();
        if(emailVerify(email) == false){
            System.out.println("Mail invalido, reigrese.");
            this.email = read.next();
        }
        //searchMail(email);
        System.out.println("Contraseña: ");
        this.password = read.next();
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

    //Esta es la funcion de calular edad para saber si es mayor o menor,
    //Use un return de tipo BOOLEAN para que sea veritificar TRUE o FALSE, pero puede refactorizarla a que retorne la edad.
    public boolean calcutaleAge(String ageBorn) {
        boolean b = true;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
