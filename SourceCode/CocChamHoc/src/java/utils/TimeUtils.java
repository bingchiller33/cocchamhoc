/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Viet
 */
public class TimeUtils {
    public static String intToTime(int timeInSeconds){
        int hours = timeInSeconds/3600;
        int minutes = (timeInSeconds - hours*3600) / 60;
        int seconds = timeInSeconds - hours*3600 - minutes*60;
        return String.format("%02d:%02d:%02d",  hours, minutes, seconds);
    }
}
