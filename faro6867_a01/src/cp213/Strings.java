package cp213;

/**
 * @author Your name and id here
 * @version 2023-05-23
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	// your code here
    // Initializing Variables
    boolean isPalindrome;
    char c1;
    char c2;
    String main = "";
    String backwards = "";
    
    // Looping string and containing the characters in main
    for (int i = 0; i<string.length();i++) {
    	c1 = string.charAt(i);
    	if (Character.isAlphabetic(c1)) {
    		main += c1;
    	}
    }
   
    // Traversing string backwards 
    for (int j = string.length()-1;j>=0;j--) {
    	c2 = string.charAt(j);
    	if (Character.isAlphabetic(c2)) {
    		backwards += c2;
    	}
    }
    // Final condition to see if string is a palindrome
    if (main.toUpperCase().equals(backwards.toUpperCase())) {
    	isPalindrome = true;
    }
    else {
    	isPalindrome = false;
    }
	return isPalindrome;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

	// your code here
    	boolean valid = true;
    	
        if (name.isEmpty() || name.equals("_")) {
            valid = false; 
        }
        char firstChar = name.charAt(0);
        if (!Character.isLetter(firstChar) && firstChar != '_') {
            valid = false; 
        }

        for (int i = 1; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!(Character.isLetter(c) || Character.isDigit(c) && c == '_')) {
                valid = false; 
            }
        }
        return valid;
    }
    
    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {
        char firstChar = word.charAt(0);
        int index = VOWELS.indexOf(firstChar);
        String newWord = ""; 
        if (index != -1) {
            newWord = word + "way";
        } else {
            for (int i = 1; i < word.length(); i++) {
                char c = word.charAt(i);
                if (VOWELS.indexOf(c) != -1) {
                    String toVowel = word.substring(0, i);
                    String end = word.substring(i);
                    if ((firstChar == 'y' || firstChar == 'Y') && i > 1) {
                        newWord = end + toVowel + "ay"; 
                    } else {
                        char newFirstChar;
                        if (Character.isUpperCase(firstChar)) {
                            newFirstChar = Character.toUpperCase(end.charAt(0));
                        } else {
                            newFirstChar = end.charAt(0);
                        }
                        newWord = newFirstChar + end.substring(1) + toVowel.toLowerCase() + "ay"; 
                    }
                    break; 
                }
            }
        }

        return newWord; 
    }
}