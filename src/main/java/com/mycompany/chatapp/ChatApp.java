/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class ChatApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Login loginSystem = new Login();
            
            System.out.println("=".repeat(70));
            System.out.println("CHAT APP - REGISTRATION");
            System.out.println("=".repeat(70));
            
            //Registaring
            System.out.println("Enter your First Name:");
            String firstname= scanner.nextLine();
            
            System.out.println("Enter your Last Name:");
            String lastName = scanner.nextLine();
            
            System.out.println("Enter Cellphone number(+27********)");
            String cellPhoneNumber = scanner.nextLine();
            
            System.out.println("Enter Username (must conatain an underscore and is no more than 5 characters in length.)");
            String username = scanner.nextLine();
            
            System.out.println("Enter Password (8 Charcters , 1 capital letter ,1 number , 1 special):");
            String password =scanner.nextLine();
            
            String registrationResults = loginSystem.registerUser(username, password, cellPhoneNumber, firstname, lastName);
            System.out.println("\n" + registrationResults);
            
            if(!registrationResults.contains("successfully")){
                System.out.println("\nRegistration failed. Please restart the applictaion");
                scanner.close();
                return;
            }
            
            
            System.out.println("\n" + "+".repeat(70));
            System.out.println("LOGIN");
            System.out.println("+".repeat(70));
            
            //Login
            System.out.print("Enter Username:");
            String loginUsername = scanner.nextLine();
            
            System.out.print("Enter Password:");
            String loginPassword = scanner.nextLine();
            
            String loginStatus = loginSystem.returnLoginStatus(loginUsername , loginPassword);
            System.out.println("\n" + loginStatus);
        }
}
}
