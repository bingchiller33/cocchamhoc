/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Yui
 */
public class UserUtils {
    public static String getRoleName(int id) {
        switch(id) {
            case 1:
                return "User";
            case 2:
                return "Lecturer";
            case 3:
                return "Admin";
            default:
                return "Unknown";
        }
    }
}
