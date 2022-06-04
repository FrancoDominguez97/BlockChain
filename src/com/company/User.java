package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    //Al constructor vacio lo uso como para poder registrarse de una al programa. Aunque podemos ver de hacerlo en una funcion
    //si es que vamos a hacer un user tipo admin.
    public User() {
        Scanner read = new Scanner(System.in);
        System.out.println("Nombre: ");
        this.name = read.next();
        System.out.println("Apellido: ");
        this.lastName = read.next();
        System.out.println("Fecha de nacimiento: ");
        this.dateOfBirth = read.next();
        if(calcutaleAge(dateOfBirth) == false){System.exit(1);}
        System.out.println("Nombre de usuario: ");
        this.userName = read.next();
        //Aca realizar una funcion para que busque si no esta en uso el user name.
        System.out.println("Email: ");
        this.email = read.next();
        //Lo mismo que el user name.
        System.out.println("Contraseña: ");
        this.password = read.next();
    }

    public User(String userName, String name, String lastName, String dateOfBirth, String email, String password) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }


    public UUID getWalletId() {
        return walletId;
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

    public String getDateOfBirth() {return dateOfBirth;}

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

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
    public List<Wallet> getWalletList() {
        return walletList;
    }
    @Override
    public String toString() {
        return "User{" +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", transactionList=" + transactionList +
                ", walletList=" + walletList +
                '}';
    }

    //Esta es la funcion de calular edad para saber si es mayor o menor,
    //Use un return de tipo BOOLEAN para que sea veritificar TRUE o FALSE, pero puede refactorizarla a que retorne la edad.
    public boolean calcutaleAge(String ageBorn){
        boolean b;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate yearOfBorn = LocalDate.parse(ageBorn,fmt);
        Period age = Period.between(yearOfBorn,LocalDate.now());
        if(age.getYears()<18){
            System.out.println("Usted es menor de edad no se puede regristrar");
            b = false;
            return b;
        }
        else {
            b = true;
            return b;
        }
    }

///Esto es lo que hizo Tincho del registro. Lo deje comentado por si no les llega a gustar o convencer lo que hice yo.
   /* public User register() {
        User user = new User();
        Scanner read = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de registro de usuarios.");
        userNameCreate();
        nameValidation();
        lastNameValidation();
        // FECHA NACIMIENTO

        // EMAIL
        //emailValidation(); ////WIP, NO LO VALIDA
        // CONTRASEÑA
        passwordValidation();
        // WALLET ID
        walletIdValidation();

        return user;
    }


    private void userNameCreate() {
        Scanner read = new Scanner(System.in);
        System.out.println("Ingrese nombre de usuario: ");
        this.userName = read.next();
        }

    private void nameValidation() {
        Scanner read = new Scanner(System.in);
        System.out.println("Ingrese su nombre: ");
        this.name = read.next();
    }

    private void lastNameValidation() {
        Scanner read = new Scanner(System.in);
        System.out.println("Ingrese su apellido: ");
        this.lastName = read.next();
    }

    private void dateOfBirthValidation() {
        String date;
        Boolean auxAdult = false;
        Scanner read = new Scanner(System.in);

        System.out.println("Ingrese su fecha de nacimiento: (yyyy-MM-dd)");
        date = read.next();
        auxAdult = adult(date);
        if (auxAdult == true){
            this.dateOfBirth = LocalDate.parse(date);
        }
    }

    private boolean adult(String date){
        //DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birth = LocalDate.parse(date); //segundo parametro del parse es el format de la linea de arriba, pero no anda, WIP
        LocalDate now = LocalDate.now();
        Period period = Period.between(birth,now);
        if(period.getYears()>=18){
            return true;
        }
        else {
            System.out.println("Usted es menor de edad, no puede registrarse en la aplicacion."); // ver como hacer para salir directamente del registro de la app (porque es menor).
            return false;
        }
    }

    private void emailValidation(){
        Scanner read = new Scanner(System.in);
        System.out.println("Ingrese su email: ");
        if(emailVerify(read.next()) == false){
            System.out.println("Mail incorrecto.");
        }
    }

    private boolean emailVerify(String mail){
        ArrayList<String> email = new ArrayList<>();
        email.add("example@gmail.com");
        email.add("exampletwo@hotmail.com");
        email.add("examplethree@yahoo.com.ar");
        email.add("examplefour@outlook.com");
        //Adding an invalid emails in list
        email.add("@helloworld.com");
        //Regular Expression
        String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regx);
        //Iterate emails array list
        for(String email1 : email){
            //Create instance of matcher
            Matcher matcher = pattern.matcher(email1);
            Boolean result = matcher.matches();
            if(result == true){
                this.email = mail;
            }
            else return false;
        }
        return true;
    }

    private void passwordValidation(){
        Scanner read = new Scanner(System.in);
        System.out.println("Ingrese su contraseña: ");
        this.password = read.next();
    }

    private void walletIdValidation(){
        this.walletId = UUID.randomUUID();
        System.out.println("Este es el ID de su wallet: " + this.walletId);
    }

    private void walletListValidation(){
        Wallet wallet = new Wallet();
        Coin utnCoin = new Coin("UTNCoin", "UTN", 100,"Moneda Oficial de la UTN MDP");
        wallet.addCoinToWallet(utnCoin);
    }

    private void transactionListValidation(){
        List<Transaction> transactionList = new ArrayList<>();
    }
*/
}



