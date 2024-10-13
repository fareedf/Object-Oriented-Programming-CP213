package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Your name and id here
 * @version 2023-05-23
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {

	// your code here
    boolean digit = false;
    if (!str.isEmpty()) {
    	int i = 0;
    	char c = str.charAt(i);
    	while (i < str.length() && Character.isDigit(c)) {
    			c = str.charAt(i);
    			i++;
    		}
    	if (i == str.length()) {
    		digit = true;
    	}
    
    	}

	return digit;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {
    
	// your code here
        boolean valid = true;
        int l = sn.length();
        
        if (l == 11) {
        char char1 = sn.charAt(0);
        char char2 = sn.charAt(1);
        char char3 = sn.charAt(2);
        char char4 = sn.charAt(7);
            
        if (char1 == 'S' && char2 == 'N' && char3 == '/') {
            int i = 3;
            while (i < 7) {
            char char5 = sn.charAt(i);
            if (!Character.isDigit(char5)) {
               valid = false;
               break; 
            }
              i++;
                }
                
                if (char4 == '-') {
                int j = 8;
                while (j < 11) {
                      char char6 = sn.charAt(j);
                      if (!Character.isDigit(char6)) {
                         valid = false;
                         break; 
                        }
                        j++;
                    }
                } else {
                    valid = false; 
                }
            } else {
                valid = false; 
            }
        } else {
            valid = false; 
        }
        
        return valid; 
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

	// your code here

	return;
    }

}
