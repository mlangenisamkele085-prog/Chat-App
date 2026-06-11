/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.util.Random;
import java.util.regex.Pattern;
/**
 *
 * @author Student
 */
public class Message {
    private long messageID;
    private String messageHash;
    private String recipientNumber;
    private String messageText;
    private String status;
    private int messageNumber;
    
    private static int totalMessagesSent = 0;
    
    // Constructor
    public Message(String recipientNumber, String messageText, int messageNumber) {
        this.messageID = generateMessageID();
        this.recipientNumber = recipientNumber;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
        this.status = "pending";
    }
    
    // 10-digit unique Message ID
    private long generateMessageID() {
        Random random = new Random();
        return 1000000000L + (long)(random.nextDouble() * 9000000000L);
    }
    
    // Check Message ID (not more than 10 digits)
    public boolean checkMessageID() {
        return String.valueOf(messageID).length() <= 10;
    }
    
    // Check recipient cell number 
    public String checkRecipientCell(String cellNumber) {
        String saPhoneNumberRegex = "\\+27[0-9]{9}$";
        if (cellNumber != null && Pattern.matches(saPhoneNumberRegex, cellNumber)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    // Create Message Hash
    // Format: first two digits of message ID : message number : first word + last word (all caps)
    public String createMessageHash() {
        String idPrefix = String.valueOf(messageID).substring(0, 2);
        String[] words = messageText.trim().split(" ");
        
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        
        // Remove punctuation
        firstWord = firstWord.replaceAll("[^a-zA-Z]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z]", "");
        
        String hash = idPrefix + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }
    
    // Validate message length (max 250 characters)
    public String validateMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }
    
    // Process message option: 1=Send, 2=Store, 0=Disregard
    public String processMessageOption(int option) {
        switch (option) {
            case 1:
                this.status = "sent";
                totalMessagesSent++;
                return "Message successfully sent.";
            case 2:
                this.status = "stored";
                return "Message successfully stored.";
            case 0:
                this.status = "disregarded";
                return "Press 0 to delete the message.";
            default:
                return "Invalid option selected.";
        }
    }
    
    // Display message details
    public String displayMessage() {
        return String.format("Message ID: %d%nMessage Hash: %s%nRecipient: %s%nMessage: %s",
                messageID, messageHash, recipientNumber, messageText);
    }
    
    // Getters
    public long getMessageID() {
        return messageID;
    }
    public String getMessageHash() { 
        return messageHash; 
    }
    public String getRecipientNumber() {
        return recipientNumber; 
    }
    public String getMessageText() { 
        return messageText;
    }
    public String getStatus() {
        return status;
    }
    public static int getTotalMessagesSent() {
        return totalMessagesSent;
    }
    
    public void setStatus(String status) {
        this.status = status; 
    }
    
        // Part 3
    
    private String senderName;
    public void setSenderName(String firstName, String lastName) {
        this.senderName = firstName + " " + lastName;
    }
    public String getSenderName() {
        return senderName;
    }
}
    
