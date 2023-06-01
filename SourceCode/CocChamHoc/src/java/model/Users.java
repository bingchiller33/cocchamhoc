/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.security.MessageDigest;
import java.sql.Date;

/**
 *
 * @author LAPTOP
 */
public class Users {
    private int     userID;
    private String  fullName;
    private String  email;
    private String  password;
    private boolean isAdmin;
    private Date    dob;
    private boolean gender;
    private String  phoneNumber;

    public Users() {
    }

    public Users(int userID, String fullName, String email, String password, boolean isAdmin, Date dob, boolean gender, String phoneNumber) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public String toMD5(String password) {
        String salt = "emwkqnahd:;wmdLDk";
        String result = null;

        // Concatenate the password with a salt value
        password = password + salt;

        try {
            // Convert the password string to bytes using UTF-8 encoding
            byte[] dataBytes = password.getBytes("UTF-8");

            // Create an instance of MessageDigest with MD5 algorithm
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Calculate the hash of the byte array
            byte[] hashBytes = md.digest(dataBytes);

            // Convert the MD5 bytes to a hexadecimal representation
            StringBuilder sb = new StringBuilder();
            for (byte hashByte : hashBytes) {
                sb.append(String.format("%02x", hashByte));
            }

            // Store the MD5 hash value as a string
            result = sb.toString();

            // Return the MD5 hash value
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return null if an exception occurs
        return result;
    }
    
}