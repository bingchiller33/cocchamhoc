/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Date;

/**
 *
 * @author Yui
 */
public class ParseUtils {
    public static int parseIntWithDefault(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    public static Date parseDateWithDefault(String str, Date defaultValue) {
        try {
            return Date.valueOf(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    
    
    public static String defaultIfEmpty(String str, String defaultValue) {
        return str == null || str.isEmpty() ? defaultValue : str;
    }
}


