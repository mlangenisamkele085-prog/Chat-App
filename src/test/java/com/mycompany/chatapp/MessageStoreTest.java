/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Student
 */
public class MessageStoreTest {
    
    public MessageStoreTest() {
    }
    
    private MessageStore messageStore;
    private Login loginSystem;
    
    @BeforeEach
    public void setUp() {
        messageStore = new MessageStore();
        loginSystem = new Login();
        loginSystem.registerUser("test_1", "Password1!", "+27831234567", "Test", "User");
        messageStore.loadTestData(loginSystem);
    }
    
    @Test
    public void testSentMessagesArrayPopulated() {
        boolean hasCakeMessage = false;
        boolean hasDinnerMessage = false;
        
        for (Message msg : messageStore.getSentMessages()) {
            if (msg.getMessageText().contains("Did you get the cake?")) {
                hasCakeMessage = true;
            }
            if (msg.getMessageText().contains("It is dinner time!")) {
                hasDinnerMessage = true;
            }
        }
        
        assertTrue(hasCakeMessage, "Sent messages should contain 'Did you get the cake?'");
        assertTrue(hasDinnerMessage, "Sent messages should contain 'It is dinner time!'");
    }
    
    @Test
    public void testLongestMessage() {
        String longest = messageStore.getLongestStoredMessage();
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }
    
    @Test
    public void testSearchByRecipient() {
        String result = messageStore.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }
    
    @Test
    public void testDeleteByMessageHash() {
        String hashToDelete = "";
        for (Message msg : messageStore.getStoredMessages()) {
            hashToDelete = msg.getMessageHash();
            break;
        }
        
        if (!hashToDelete.isEmpty()) {
            String result = messageStore.deleteByMessageHash(hashToDelete);
            assertTrue(result.contains("successfully deleted"));
        }
    }
    
    @Test
    public void testStoredMessagesNotEmpty() {
        assertFalse(messageStore.getStoredMessages().isEmpty());
    }
    
    @Test
    public void testMessageHashesArray() {
        assertFalse(messageStore.getMessageHashes().isEmpty());
    }
    
    @Test
    public void testMessageIDsArray() {
        assertFalse(messageStore.getMessageIDs().isEmpty());
    }
}
    

