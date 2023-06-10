/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Yui
 */
public class SortUtils {

    public static String getNextSort(String sort) {
        switch(sort) {
            case "none":
                return "asc";
            case "asc":
                return "desc";
            case "desc":
                return "none";
            default:
                return "asc";
        }
    }
}
