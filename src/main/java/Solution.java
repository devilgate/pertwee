/*
 * This was a Codility test, to compare two strings that were supposed to have 
 * come from an OCR source, and determine whether the could have the same origin.
 * Unrecognised characters were replace by '?', and then further replace by a 
 * count of consecutive question marks.
 * 
 * I spent too long focusing on working out the lengths of the original strings,
 * and never got to doing a character-by-character comparison.
 */

import java.util.Arrays;
import java.util.List;

class Solution {
    
    private static final List<Character> DIGITS = Arrays.asList('0', '1', '2','3', '4', '5', '6', '7', '8', '9' );
    
    public boolean solution(String s, String t) {
        
        System.out.printf("S is %s; T is %s%n", s, t);
        
        // Short-circuit for trivial case of identical strings
        if (s.equals(t)) {
            
            return true;
        }
     
        /* I'm not actually sure this is soluble if the original text could 
         * contain digits. If the input string was '12345', for example, the OCR
         * could be '?????', which reduces to '5'. But it could also be '????5', 
         * which reduces to '45'. How can we distinguish between those and the 
         * actual input string '5' and '45'? And '45' could represent a string 
         * 45 unrecognised characters.
         * 
         * 
         * So I'm going to work on the assumption that the original text does not
         * contain digits.
         */
        
        // Otherwise we want to calculate the lengths by expanding out the groups of 
        // digits in each string.
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        
        if (countChars(sArr) != countChars(tArr)) {
            
            return false;
        }
        
        return true;
    }   
    
    private int countChars(char[] arr) {
        
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            
            char c = arr[i];
            System.out.printf("Index is %d; character is %s%n", i, c);
            if (isDigit(c)) {

                System.out.printf("%s is a digit%n", c);
                // Look ahead to see if there are more digits
                StringBuilder number = new StringBuilder();
                number.append(c);
                System.out.printf("Newly-created StringBuilder is %s%n", number.toString());
                if (i < arr.length - 2) {
                    while (isDigit(arr[i + 1])) {

                        number.append(arr[++i]);
                        System.out.printf("index is now %d; character is %s%n", i, arr[i]);
                    } 
                }
                // When we get here the StringBuilder contains a string of digits,
                // and i points to the next non-digit element (or is past the end,
                // so the for-loop will end).
                System.out.printf("StringBuilder contains %s%n", number.toString());
                count = count + Integer.parseInt(number.toString());
                
            } else {
                
                count++;
            }
            
        }
        
        System.out.printf("Returning count: %d%n", count);
        return count;
    }
    
    private boolean isDigit(char c) {
        
        if (DIGITS.contains(c)) {
            return true;
        }
        
        return false;
    }
}