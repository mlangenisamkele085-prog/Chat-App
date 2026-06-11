/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Student
 */
public class MessageStore {
 
    
    private List<Message> sentMessages;
    private List<Message> disregardedMessages;
    private List<Message> storedMessages;
    private List<String> messageHashes;
    private List<Long> messageIDs;
    
    public MessageStore() {
        sentMessages = new ArrayList<>();
        disregardedMessages = new ArrayList<>();
        storedMessages = new ArrayList<>();
        messageHashes = new ArrayList<>();
        messageIDs = new ArrayList<>();
    }
    
    //  message to appropriate list 
    public void addMessage(Message message) {
       
        messageIDs.add(message.getMessageID());
        messageHashes.add(message.getMessageHash());
        
        // Add to specific list based on status
        switch (message.getStatus()) {
            case "sent":
                sentMessages.add(message);
                break;
            case "stored":
                storedMessages.add(message);
                break;
            case "disregarded":
                disregardedMessages.add(message);
                break;
        }
    }
    
    //  test data
    public void loadTestData(Login loginSystem) {
        // Test Data Message 1 - Sent
        Message m1 = new Message("+27834557896", "Did you get the cake?", 1);
        m1.setStatus("sent");
        m1.setSenderName(loginSystem.getfirstName(), loginSystem.getlastName());
        addMessage(m1);
        
        // Test Data Message 2 - Stored
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 2);
        m2.setStatus("stored");
        m2.setSenderName(loginSystem.getfirstName(), loginSystem.getlastName());
        addMessage(m2);
        
        // Test Data Message 3 - Disregarded
        Message m3 = new Message("+27834484567", "Yohoooo, I am at your gate.", 3);
        m3.setStatus("disregarded");
        m3.setSenderName(loginSystem.getfirstName(), loginSystem.getlastName());
        addMessage(m3);
        
        // Test Data Message 4 - Sent
        Message m4 = new Message("0838884567", "It is dinner time!", 4);
        m4.setStatus("sent");
        m4.setSenderName(loginSystem.getfirstName(), loginSystem.getlastName());
        addMessage(m4);
        
        // Test Data Message 5 - Stored
        Message m5 = new Message("+27838884567", "Ok, I am leaving without you.", 5);
        m5.setStatus("stored");
        m5.setSenderName(loginSystem.getfirstName(), loginSystem.getlastName());
        addMessage(m5);
    }
    
  
    public String displayStoredMessagesSummary() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }
        
        StringBuilder result = new StringBuilder();
        result.append("STORED MESSAGES SUMMARY");
        result.append("=".repeat(50)).append("\n");
        
        for (Message msg : storedMessages) {
            result.append("Sender: ").append(msg.getSenderName()).append("\n");
            result.append("Recipient: ").append(msg.getRecipientNumber()).append("\n");
            result.append("-".repeat(30)).append("\n");
        }
        return result.toString();
    }
    
    // Display the longest stored message
    public String getLongestStoredMessage() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }
        
        Message longest = storedMessages.get(0);
        for (Message msg : storedMessages) {
            if (msg.getMessageText().length() > longest.getMessageText().length()) {
                longest = msg;
            }
        }
        return longest.getMessageText();
    }
    
    //Search for a message ID and display recipient and message
    public String searchByMessageID(long messageID) {
        for (Message msg : storedMessages) {
            if (msg.getMessageID() == messageID) {
                return "Recipient: " + msg.getRecipientNumber() + "\nMessage: " + msg.getMessageText();
            }
        }
        for (Message msg : sentMessages) {
            if (msg.getMessageID() == messageID) {
                return "Recipient: " + msg.getRecipientNumber() + "\nMessage: " + msg.getMessageText();
            }
        }
        return "Message ID not found.";
    }
    
    //  Search all messages for a recipient
    public String searchByRecipient(String recipientNumber) {
        StringBuilder result = new StringBuilder();
        boolean found = false;
        
        for (Message msg : storedMessages) {
            if (msg.getRecipientNumber().equals(recipientNumber)) {
                result.append(msg.getMessageText()).append("\n");
                found = true;
            }
        }
        for (Message msg : sentMessages) {
            if (msg.getRecipientNumber().equals(recipientNumber)) {
                result.append(msg.getMessageText()).append("\n");
                found = true;
            }
        }
        
        return found ? result.toString().trim() : "No messages found for recipient: " + recipientNumber;
    }
    
    //Delete a message using message hash
    public String deleteByMessageHash(String messageHash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(messageHash)) {
                String messageText = storedMessages.get(i).getMessageText();
                storedMessages.remove(i);
                return "Message: \"" + messageText + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }
    
    //Display full report of all stored messages
    public String displayFullReport() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }
        
        StringBuilder result = new StringBuilder();
        result.append(" FULL STORED MESSAGES REPORT");
        result.append("=".repeat(60)).append("\n");
        
        for (Message msg : storedMessages) {
            result.append("Message Hash: ").append(msg.getMessageHash()).append("\n");
            result.append("Recipient: ").append(msg.getRecipientNumber()).append("\n");
            result.append("Message: ").append(msg.getMessageText()).append("\n");
            result.append("-".repeat(40)).append("\n");
        }
        return result.toString();
    }
    
   
    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }
    public List<String> getMessageHashes() { return messageHashes; }
    public List<Long> getMessageIDs() { return messageIDs; }
}

