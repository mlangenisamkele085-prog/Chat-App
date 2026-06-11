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
public class ChatAppPart3 {
   

    private static MessageStore messageStore;
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Login loginSystem = new Login();
            List<Message> allMessages = new ArrayList<>();
            messageStore = new MessageStore();
            
            System.out.println("=".repeat(70));
            System.out.println("CHAT APP - REGISTRATION");
            System.out.println("=".repeat(70));
            
            // Registration (SAME AS YOUR PART 2)
            System.out.print("Enter your First Name: ");
            String firstname = scanner.nextLine();
            
            System.out.print("Enter your Last Name: ");
            String lastName = scanner.nextLine();
            
            System.out.print("Enter Cellphone number (+27********): ");
            String cellPhoneNumber = scanner.nextLine();
            
            System.out.print("Enter Username (must contain underscore, max 5 chars): ");
            String username = scanner.nextLine();
            
            System.out.print("Enter Password (8 chars, 1 capital, 1 number, 1 special): ");
            String password = scanner.nextLine();
            
            String registrationResults = loginSystem.registerUser(username, password, cellPhoneNumber, firstname, lastName);
            System.out.println(registrationResults);
            
            if (!registrationResults.contains("successfully")) {
                System.out.println("Registration failed. Please restart the application.");
                scanner.close();
                return;
            }
            
            // Login 
            System.out.println("=".repeat(70));
            System.out.println("LOGIN");
            System.out.println("=".repeat(70));
            
            System.out.print("Enter Username: ");
            String loginUsername = scanner.nextLine();
            
            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();
            
            String loginStatus = loginSystem.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginStatus);
            
            if (!loginStatus.contains("Welcome")) {
                System.out.println("Login failed. Exiting application.");
                scanner.close();
                return;
            }
            
            //test data 
            messageStore.loadTestData(loginSystem);
            
           
            System.out.println("=".repeat(70));
            System.out.println("Welcome to QuickChat.");
            System.out.println("=".repeat(70));
            
            boolean running = true;
            while (running) {
                System.out.println();
                System.out.println("=".repeat(70));
                System.out.println("MAIN MENU");
                System.out.println("=".repeat(70));
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently Sent Messages");
                System.out.println("3. Stored Messages"); 
                System.out.println("4. Quit");             
                
                System.out.print("Choose an option (Number): ");
                int choice;
                
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }
                
                switch (choice) {
                    case 1:
                        sendMessages(scanner, allMessages, loginSystem);
                        break;
                    case 2:
                        showRecentMessages();
                        break;
                    case 3:
                        storedMessagesMenu(scanner);  
                        break;
                    case 4:
                        System.out.println("Goodbye! Thanks for using ChatAPP.");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
    
    private static void sendMessages(Scanner scanner, List<Message> allMessages, Login loginSystem) {
        
        System.out.println("SEND MESSAGES");
        System.out.println("=".repeat(70));
        
        System.out.print("How many messages do you want to send? ");
        int numMessages;
        try {
            numMessages = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Returning to menu.");
            return;
        }
        
        for (int i = 1; i <= numMessages; i++) {
            System.out.println("Message " + i + " of " + numMessages);
            System.out.println("=".repeat(70));
            
            System.out.print("Enter recipient number (+27XXXXXXXXX): ");
            String recipient = scanner.nextLine();
            
            Message tempMsg = new Message(recipient, "temp", i);
            String recipientValidation = tempMsg.checkRecipientCell(recipient);
            if (!recipientValidation.equals("Cell phone number successfully captured.")) {
                System.out.println("Error: " + recipientValidation);
                i--;
                continue;
            }
            
            System.out.print("Enter your message (max 250 characters): ");
            String messageText = scanner.nextLine();
            
            String lengthValidation = tempMsg.validateMessageLength(messageText);
            if (!lengthValidation.equals("Message ready to send.")) {
                System.out.println("Error: " + lengthValidation);
                i--;
                continue;
            }
            
            Message message = new Message(recipient, messageText, i);
            message.setSenderName(loginSystem.getfirstName(), loginSystem.getlastName());
            
            System.out.println("=".repeat(70));
            System.out.println("Message Preview:");
            System.out.println("=".repeat(70));
            System.out.println(message.displayMessage());
            System.out.println("=".repeat(70));
            
            System.out.println("Options:");
            System.out.println("=".repeat(70));
            System.out.println("1. Send Message");
            System.out.println("2. Store Message");
            System.out.println("3. Disregard Message");
            
            System.out.print("Choose an option (Number): ");
            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                option = 0;
            }
            
            String result = message.processMessageOption(option);
            System.out.println(result);
            
            if (option == 1 || option == 2) {
                allMessages.add(message);
                messageStore.addMessage(message);
            }
            
            System.out.println("=".repeat(70));
            System.out.println("Message Details:");
            System.out.println("=".repeat(70));
            System.out.println("Message ID: " + message.getMessageID());
            System.out.println("Message Hash: " + message.getMessageHash());
            System.out.println("Recipient: " + message.getRecipientNumber());
            System.out.println("Message: " + message.getMessageText());
        }
        
        System.out.println("=".repeat(70));
        System.out.println("Total messages sent: " + Message.getTotalMessagesSent());
        System.out.println("=".repeat(70));
    }
    
    private static void showRecentMessages() {
        
        System.out.println("=".repeat(70));
        System.out.println("RECENT SENT MESSAGES");
        System.out.println("=".repeat(70));
        
        List<Message> sent = messageStore.getSentMessages();
        if (sent.isEmpty()) {
            System.out.println("No sent messages found.");
        } else {
            for (Message msg : sent) {
                System.out.println("To: " + msg.getRecipientNumber());
                System.out.println("Message: " + msg.getMessageText());
                System.out.println("-".repeat(30));
            }
        }
    }
    
    // Part 3
    private static void storedMessagesMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("=".repeat(70));
            System.out.println("STORED MESSAGES MENU");
            System.out.println("=".repeat(70));
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display the longest stored message");
            System.out.println("c. Search for a message ID");
            System.out.println("d. Search all messages for a recipient");
            System.out.println("e. Delete a message using message hash");
            System.out.println("f. Display full report");
            System.out.println("g. Back to Main Menu");
            System.out.println("=".repeat(70));
            
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().toLowerCase();
            
            switch (option) {
                case "a":
                    System.out.println(messageStore.displayStoredMessagesSummary());
                    break;
                case "b":
                    System.out.println("\n📏 Longest Stored Message:");
                    System.out.println("=".repeat(50));
                    System.out.println(messageStore.getLongestStoredMessage());
                    break;
                case "c":
                    System.out.print("Enter Message ID: ");
                    try {
                        long id = Long.parseLong(scanner.nextLine());
                        System.out.println(messageStore.searchByMessageID(id));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Message ID.");
                    }
                    break;
                case "d":
                    System.out.print("Enter Recipient Number: ");
                    String recipient = scanner.nextLine();
                    System.out.println("\n📧 Messages for " + recipient + ":");
                    System.out.println("=".repeat(50));
                    System.out.println(messageStore.searchByRecipient(recipient));
                    break;
                case "e":
                    System.out.print("Enter Message Hash to delete: ");
                    String hash = scanner.nextLine();
                    System.out.println(messageStore.deleteByMessageHash(hash));
                    break;
                case "f":
                    System.out.println(messageStore.displayFullReport());
                    break;
                case "g":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

