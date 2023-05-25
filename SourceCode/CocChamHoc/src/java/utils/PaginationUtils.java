/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yui
 */
public class PaginationUtils {
    
    public static List<Integer> getWindow(int current, int size, int outputSize) {
        ArrayList<Integer> rs = new ArrayList<>();
        if(size <= outputSize) {
            for(int i = 1; i <= size; i++) {
                rs.add(i);
            }
        } else if (current <= outputSize / 2) {
            for(int i = 1; i <= outputSize; i++){
                rs.add(i);
            }
        } else if (current >= (size - outputSize / 2)) {
            for(int i = size - outputSize + 1; i <= size; i++) {
                rs.add(i);
            }
        } else {
            int start = current - outputSize / 2;
            int end = current + outputSize / 2;
            if (outputSize % 2 == 0) {
                end += 1;
            }
            
            for(int i = start; i <= end; i++ ) {
                rs.add(i);
            }
        }
        
        return rs;
    }
    
    public static boolean contains(List<Integer> a, int b) {
        return a.contains(b);
    }
}
