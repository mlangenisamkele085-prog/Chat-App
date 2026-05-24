/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Student
 */
public class MessageTest {

    private Message message;
    
    @BeforeEach
    public void setUp() {
        message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 1);
    }
    
    @Test
    public void testMessageIDLength() {
        assertTrue(message.checkMessageID());
        assertTrue(message.getMessageID() >= 1000000000L);
        assertTrue(message.getMessageID() <= 9999999999L);
    }
    
    @Test
    public void testRecipientNumberCorrect() {
        String result = message.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }
    
    @Test
    public void testRecipientNumberIncorrect() {
        String result = message.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }
    
    @Test
    public void testMessageHashFormat() {
        String hash = message.createMessageHash();
        assertNotNull(hash);
        assertTrue(hash.matches("\\d{2}:\\d+:[A-Z]+"));
    }
    
    @Test
    public void testMessageLengthValid() {
        String shortMessage = "Hi Mike, can you join us for dinner tonight?";
        String result = message.validateMessageLength(shortMessage);
        assertEquals("Message ready to send.", result);
    }
    
    @Test
    public void testMessageLengthInvalid() {
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            longMessage.append("a");
        }
        String result = message.validateMessageLength(longMessage.toString());
        assertTrue(result.contains("exceeds 250 characters by"));
        assertTrue(result.contains("please reduce the size"));
    }
    
    @Test
    public void testSendMessageOption() {
        String result = message.processMessageOption(1);
        assertEquals("Message successfully sent.", result);
        assertEquals("sent", message.getStatus());
    }
    
    @Test
    public void testStoreMessageOption() {
        String result = message.processMessageOption(2);
        assertEquals("Message successfully stored.", result);
        assertEquals("stored", message.getStatus());
    }
    
    @Test
    public void testDisregardMessageOption() {
        String result = message.processMessageOption(0);
        assertEquals("Press 0 to delete the message.", result);
        assertEquals("disregarded", message.getStatus());
    }
    
    @Test
    public void testMessageDisplay() {
        String display = message.displayMessage();
        assertTrue(display.contains("Message ID:"));
        assertTrue(display.contains("Message Hash:"));
        assertTrue(display.contains("Recipient:"));
        assertTrue(display.contains("Message:"));
    }
}

