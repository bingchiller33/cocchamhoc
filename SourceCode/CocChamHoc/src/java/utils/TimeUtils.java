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
    public static int timeToInt(String time){
        int duration = 0;
        String []durations =time.split(":");
        for (int i = 0; i < durations.length; i++) {
            if(i==0)
                duration += Integer.parseInt(durations[i])*3600;
            if(i==1)
                duration += Integer.parseInt(durations[i])*60;
            else
                duration+=Integer.parseInt(durations[i]);
        }                    
        return duration;
    }
    public static int toMins(int time){
        return time/60;
    }
}
