/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.util.regex.Pattern;

/**
 *
 * @author Student
 */
public class Login { 
    private String password;
    private String username;
     private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
    public Login() {}
    
    public boolean checkUserName(String username){
        return username != null && username .contains("_") && username.length() <= 5 ;
    }
    //check the password complicity ; must have most 8 characters ; contain a capitla letter , number ,
    // and special characters
    
    public boolean checkPasswordComplexity(String password){
        if (password == null || password.length()<8){
        return false ;
    }
    boolean hasCapital = false ;
    boolean hasNumber = false ;
    boolean hasSpecial = false 
    
    for (char c : password.toCharArray()){
    if (Character.isUpperCase(c)){
        hasCapital = true ;
    }else if (Character.isDigit(c)){
    hasNumber = true;
    }else if (!Character.isLetterOrDigit(c) ){
    hasSpecial = true;
}
} 
    return hasCapital && hasSpecial && hasNumber;
}

//Check cell phone format 
//must be south Africna and contain an international code
public boolean checkCellPhoneNumber(String cellPhoneNumber){
    String saPhoneNumberRegex = "\\+27[0-9]{9}$" ;
    return cellPhoneNumber != null && Pattern.matches(saPhoneNumberRegex, cellPhoneNumber);
   }

//return message and regiter the user
public String registerUser(String username , String password , String cellPhoneNumber , String firstName , String lastName){
    if (!checkUserName(username)){
        return "Username is not correctly formatted. Please ensure that your username contain an underscore and is no more longer than five characters in length.";
    } 
    if (!checkPasswordComplexity(password)){
        return "Password is not correctly formatted. Please ensure that your password contains atleast a capital letter, a number, a special character and has atleast 8 characters.";
    }
    if (!checkCellPhoneNumber(cellPhoneNumber)){
        return"Cell phone number was not correctly formatted or does not contain an iternational code";
    }
    
    //and if all vallidation passed  user detail should be stored
    
    this.cellPhoneNumber = cellPhoneNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.username = username;
    
    return"Username is succesfully captured. \nPassword successfully captured .\nCellphone number succesfully captured. ";
    
}
// verify login credentials
public boolean loginUser(String enteredUsername , String enteredPassword){
    return this.username!=null &&
            this.username.equals(enteredUsername)&&
            this.password!= null &&
            this.password.equals(enteredPassword);
}
// reurn login status message
public String returnLoginStatus(String enteredUsername , String enteredPassword){
    if (loginUser(enteredUsername , enteredPassword)){
        return "Welcome " + firstName +" "+ lastName + ", it is great to see you again.";
    }else{
        return "Username or password entered is incorrect.Please try again";
    }
}
//getter for testing 
public String getUsername(){return username;}
public String getpassword() {return password;}
public String getfirstName() {return firstName;}
public String getlastName(){return lastName;}
public String getcellPhoneNumber() {return cellPhoneNumber;} 

    
}
