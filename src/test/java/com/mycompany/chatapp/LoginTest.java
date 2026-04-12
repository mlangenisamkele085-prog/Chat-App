/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    
    private Login login;
    
    @BeforeEach  // JUnit 5 uses @BeforeEach instead of @Before
    public void setUp() {
        login = new Login();
    }
    
    @Test
    public void testCheckUserName_Valid() {
        assertTrue(login.checkUserName("mph_1"));
        assertTrue(login.checkUserName("o_wam"));
    }
    
    @Test
    public void testCheckUserName_NoUnderscore() {
        assertFalse(login.checkUserName("mpho"));
    }
    
    @Test
    public void testCheckUserName_TooLong() {
        assertFalse(login.checkUserName("mpho_246"));
    }
    
    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(login.checkPasswordComplexity("Password1!"));
    }
    
    @Test
    public void testCheckPasswordComplexity_TooShort() {
        assertFalse(login.checkPasswordComplexity("Pass1!"));
    }
    
    @Test
    public void testCheckPasswordComplexity_NoCapital() {
        assertFalse(login.checkPasswordComplexity("password1!"));
    }
    
    @Test
    public void testCheckPasswordComplexity_NoNumber() {
        assertFalse(login.checkPasswordComplexity("Password!"));
    }
    
    @Test
    public void testCheckPasswordComplexity_NoSpecialChar() {
        assertFalse(login.checkPasswordComplexity("Password123"));
    }
    
    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
    }
    
    @Test
    public void testCheckCellPhoneNumber_NoPlusPrefix() {
        assertFalse(login.checkCellPhoneNumber("27831234567"));
    }
    
    @Test
    public void testCheckCellPhoneNumber_WrongCountryCode() {
        assertFalse(login.checkCellPhoneNumber("+12831234567"));
    }
    
    @Test
    public void testCheckCellPhoneNumber_WrongLength() {
        assertFalse(login.checkCellPhoneNumber("+2783123456"));
    }
    
    @Test
    public void testRegisterUser_Valid() {
        String result = login.registerUser("mph_1", "Password1!", "+27831234567", "mpho", "Owami");
        assertTrue(result.contains("successfully captured"));
        assertEquals("mph_1", login.getUsername());
    }
    
    @Test
    public void testRegisterUser_InvalidUsername() {
        String result = login.registerUser("mpho246", "Password1!", "+27831234567", "mpho", "Owami");
        assertTrue(result.contains("not correctly formatted"));
    }
    
    @Test
    public void testRegisterUser_InvalidPassword() {
        String result = login.registerUser("mph_1", "password", "+27831234567", "mpho", "Owami");
        assertTrue(result.contains("not correctly formatted"));
    }
    
    @Test
    public void testRegisterUser_InvalidPhoneNumber() {
        String result = login.registerUser("mp4_5", "Password1!", "0831234567", "mpho", "Owami");
        assertTrue(result.contains("not correctly formatted"));
    }
    
    @Test
    public void testLoginUser_Success() {
        login.registerUser("mph_1", "Password1!", "+27831234567", "mpho", "Owami");
        assertTrue(login.loginUser("mph_1", "Password1!"));
    }
    
    @Test
    public void testLoginUser_WrongPassword() {
        login.registerUser("mph_1", "Password1!", "+27831234567", "mpho", "Owami");
        assertFalse(login.loginUser("mph_1", "WrongPass!"));
    }
    
    @Test
    public void testReturnLoginStatus_Success() {
        login.registerUser("mph_1", "Password1!", "+27831234567", "mpho", "Owami");
        String result = login.returnLoginStatus("mph_1", "Password1!");
        assertEquals("Welcome mpho Owami, it is great to see you again.", result);
    }
    
    @Test
    public void testReturnLoginStatus_Failure() {
        login.registerUser("mp4_5", "Password1!", "+27831234567", "mpho", "Owami");
        String result = login.returnLoginStatus("wrong", "wrong");
        assertEquals("Username or password entered is incorrect. Please try again", result);
    }
}