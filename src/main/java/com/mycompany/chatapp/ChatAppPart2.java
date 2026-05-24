/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class ChatAppPart2 {

    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Login loginSystem = new Login();
            List<Message> allMessages = new ArrayList<>();
            
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
            System.out.println( registrationResults);
            
            if(!registrationResults.contains("successfully")){
                System.out.println("Registration failed. Please restart the applictaion");
                scanner.close();
                return;
            }
            
            
            System.out.println("=".repeat(70));
            System.out.println("LOGIN");
            System.out.println("=".repeat(70));
            
            //Login
            System.out.print("Enter Username:");
            String loginUsername = scanner.nextLine();
            
            System.out.print("Enter Password:");
            String loginPassword = scanner.nextLine();
            
            String loginStatus = loginSystem.returnLoginStatus(loginUsername , loginPassword);
            System.out.println(loginStatus);
            
            if (!loginStatus.contains("Welcome")) {
                System.out.println("Login failed. Exiting application.");
                scanner.close();
                return;
            }
            
            //MESSAGING MENU
            System.out.println("=".repeat(70));
            System.out.println("Welcome to QuickChat.");
            System.out.println("=".repeat(70));
            
            boolean running = true;
            while (running) {
                System.out.println();
                System.out.println("=".repeat(70));
                System.out.println("MAIN MENU");
                System.out.println("=".repeat(70));
                System.out.println("1.Send Messages");
                System.out.println("2.Show recently Sent Messages");
                System.out.println("3.Quit");
               
                System.out.print("Choose an option(Number): ");
                int choice;
                
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }
                System.out.println("=".repeat(70));
                switch (choice) {
                    case 1:
                        sendMessages(scanner, allMessages);
                        break;
                    case 2:
                        System.out.println("Recent messages feature is still going to be developed.");
                        break;
                    case 3:
                        System.out.println("Goodbye!. Thanks for using ChatAPP.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
    
    private static void sendMessages(Scanner scanner, List<Message> allMessages) {
        System.out.println("SEND MESSAGES");
        System.out.println("=".repeat(70));
        
        System.out.print("How many messages do you want to send? ");
        int numMessages;
        try {
            numMessages = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Invalid number. Returning to menu.");
            return;
        }
        
        for (int i = 1; i <= numMessages; i++) {
            System.out.println("Message " + i + " of " + numMessages);
            System.out.println("=".repeat(70));
            
            // Get recipient number
            System.out.print("Enter recipient number (+27XXXXXXXXX): ");
            String recipient = scanner.nextLine();
            
            // Validate recipient
            Message tempMsg = new Message(recipient, "temp", i);
            String recipientValidation = tempMsg.checkRecipientCell(recipient);
            if (!recipientValidation.equals("Cell phone number successfully captured.")) {
                System.out.println("Wrong " + recipientValidation);
                i--; // Retry this message
                continue;
            }
            
            // Get message text
            System.out.print("Enter your message (max 250 characters): ");
            String messageText = scanner.nextLine();
            
            // Validate message length
            String lengthValidation = tempMsg.validateMessageLength(messageText);
            if (!lengthValidation.equals("Message ready to send.")) {
                System.out.println("Wrong " + lengthValidation);
                i--; // Retry this message
                continue;
            }
            
            // Create the actual message
            Message message = new Message(recipient, messageText, i);
            
            // Display message preview
            System.out.println("=".repeat(70));
            System.out.println("Message Preview:");
            System.out.println("=".repeat(70));
            System.out.println(message.displayMessage());
            System.out.println("=".repeat(70));
            
            // Get user choice
            System.out.println("Options:");
            System.out.println("=".repeat(70));
            System.out.println("1.Send Messages");
            System.out.println("2.Store Message to be sent later (coming soon in development)");
            System.out.println("3.Disregard Message");
            
            System.out.print("Choose an option(Number): ");
            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                option = 0;
            }
            
            String result = message.processMessageOption(option);
            System.out.println(result);
            
            // Store if sent or stored
            if (option == 1 || option == 2) {
                allMessages.add(message);
            }
            
            // Display final message info as required by POE
            System.out.println("=".repeat(70));
            System.out.println("Message Details:");
            System.out.println("=".repeat(70));
            System.out.println("Message ID: " + message.getMessageID());
            System.out.println("Message Hash: " + message.getMessageHash());
            System.out.println("Recipient: " + message.getRecipientNumber());
            System.out.println("Message: " + message.getMessageText());
        }
        
        // Display total messages sent
        System.out.println("=".repeat(70));
        System.out.println("Total messages sent: " + Message.getTotalMessagesSent());
        System.out.println("=".repeat(70));
    }
}  

