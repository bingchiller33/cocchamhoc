/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.security.MessageDigest;

/**
 *
 * @author LAPTOP
 */
public class EncryptionUtils {

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
