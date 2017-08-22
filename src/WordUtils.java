import java.util.*;

/**
 * @author Laurie Dugdale
 */
public class WordUtils {

    /**
     * Task 1
     * Create a function which counts the number of occurrences of a given letter in a string.
     *
     * @param letter The letter to count the occurrences of
     * @param text The String to search for the letter
     * @return The number of occurances of the letter in the text
     */
    public int countLetterInString(char letter, String text){

        // null check
        if (text == null){
            throw new IllegalArgumentException("text String was null");
        }

        int count = 0;
        // loop through the text
        for(char c : text.toCharArray()){
            // if current letter in the text matches the given letter
            if (c == letter){
                count++;
            }
        }

        System.out.println(count);
        return count;
    }


    /**
     * Task 2
     * Create a function which decides if a string is a palindrome.
     *
     * @param text the text to check
     * @return returns true if text is a palindrome
     */
    public boolean isPalindrome(String text){

        // null check
        if (text == null){
            return true;
        }

        // remove all non letter characters from the string, not necessarily the most efficient way, but the most concise.
        text = text.replaceAll("[^a-zA-Z]", "").toLowerCase();

        // save the text length
        int n = text.length();
        // change to false if not a palindrome
        boolean isPal = true;
        // loop through half of the text
        for (int i = 0; i < n/2; i++) {
            // if the character at i does not match the character at the 'opposite' side of the String (n - i - 1), its not a palindrome.
            if (text.charAt(i) != text.charAt(n - i - 1)) {
                isPal = false;
            }
        }

        System.out.println(isPal);
        return isPal;
    }

    /**
     * Task 3a
     * Create a function which counts the number of occurrences of words from a "censored words list" in a text.
     *
     * @param censored the array of censored words to search for.
     * @param text The text in which to search for the censored words.
     * @return A formatted string display the censored words found and their totals.
     */
    public String countCensoredWords(String [] censored, String text){

        // null check
        if (censored == null){
            throw new IllegalArgumentException("censored array was null");
        } else if (text == null){
            throw new IllegalArgumentException("text String was null");
        }

        // using a TreeMap to retain alphabetical order, and to store key, value pairs instead of creating a separate object.
        Map<String, Integer> censorOccurances = new TreeMap<>();

        // split the string at any non letter character
        String[] words = text.split("\\W+");
        // variable to keep track of the overall total
        int total = 0;
        // loop through the array of words created by the split
        for (String s : words){
            // loop through the censored words array. Would have liked to use a set for O(1) lookup time but, noticed the given results worked for partial matches
            for (String c : censored) {
                // the word contains a censored word
                if (contains(s,c)){
                    // increment overall total
                    total ++;
                    // num representing the current count of the particular censored word occurance
                    int num = 0;
                    // null check to avoid errors
                    if (censorOccurances.get(c) != null){
                        num = censorOccurances.get(c) + 1;
                    // if value was null then initialise to 1
                    } else {
                        num = 1;
                    }

                    censorOccurances.put(c, num);
                }
            }
        }

        // more efficient to use a StringBuffer
        StringBuffer sb = new StringBuffer();
        // create the string
        censorOccurances.forEach((k,v) -> sb.append(k + ": " + v + ", "));
        // append the total
        sb.append("total: " + total);

        System.out.println(sb);
        return sb.toString();

    }

    /**
     * Task 3b
     * Create a way to censor words featured in the "censored words list" that appear in the text.
     *
     * @param censored
     * @param text
     * @return
     */
    public String censorWords(String [] censored, String text){

        // null check
        if (censored == null){
            throw new IllegalArgumentException("censored array was null");
        } else if (text == null){
            throw new IllegalArgumentException("text String was null");
        }

        // split the string at empty spaces
        String[] words = text.split(" ");

        // loop through words
        for (int i = 0; i < words.length; i++){
            // loop through empty words
            for (int j = 0; j < censored.length; j++){
                // if word contains censored word
                if (contains(words[i], censored[j])){
                    // use the replacecharacters method to censor the word
                    words[i] = replaceCharacters(words[i]);
                }
            }
        }
        // create output string
        String output = String.join(" ", words);

        System.out.println(output);
        return output;
    }

    /**
     * Task 3c
     * Create a way to censor a single word palindrome in a text.
     *
     * @param text
     * @return
     */
    public String censorSingleWordPalindromes(String text){

        // null check
        if (text == null){
            throw new IllegalArgumentException("text String was null");
        }

        // split text at empty spaces
        String[] words = text.split(" ");

        // loop through words
        for (int i = 0; i < words.length; i++){
            // use previously made method in task 2 to check if current word is palindrome
            if (isPalindrome(words[i])){
                // if it is a palindrome use the replace characters method to censor the word
                words[i] = replaceCharacters(words[i]);
            }
        }

        // create the string
        String output = String.join(" ", words);

        System.out.println(output);
        return output;

    }

    /**
     * Helper method
     * this method replaces all characters except the first and last character with a '$'
     *
     * @param word the word to modify - must not be null
     * @return the modified word
     */
    private String replaceCharacters(String word){

        char [] a = word.toCharArray();

        // get start and end position
        int start = 0;
        int end = a.length - 1;
        // variable to signify when the first alphabet character at the start and end have been found
        boolean startFound = false;
        boolean endFound = false;

        // loop while start or end have not been found and start value doesn't equal the end value
        while(!endFound || !startFound && start != end){
            // if character is a letter set startFound to true
            if (Character.isLetter(a[start])){
                startFound = true;
            // else increment start
            } else {
                start++;
            }
            // if character is a letter set endFound to true
            if (Character.isLetter(a[end])){
                endFound = true;
            // else decrement end
            } else {
                end --;
            }
        }

        // change values between start and end to the '$' character
        for (int i = start + 1 ; i < end; i++){
            a[i] = '$';
        }

        return new String(a);
    }

    /**
     * Helper method
     *
     * @param word the word to check
     * @param censored the censored word to check for
     * @return true if word contains censored
     */
    private boolean contains( String word, String censored ) {
        // null check if word is null set to empty string
        word = word == null ? "" : word;
        censored = censored == null ? "" : censored;

        return word.toLowerCase().contains( censored.toLowerCase() );
    }

    /**
     * performing quick basic tests with the given values.
     */
    public static void main(String[] args) {

        WordUtils w = new WordUtils();

//        w.countLetterInString('e', "I have some cheese");
//
//        w.isPalindrome("God saved Eva’s dog");
//
        String [] a = {"dog", "cat", "large"};
        w.countCensoredWords(a, "I have a cat named Meow and a dog name Woof. I love the dog a lot. He is larger than a small horse.");

//        String [] b = {"meow", "woof"};
//        w.censorWords(b, "I have a cat named Meow and a dog name Woof. I love the dog a lot. He is larger than a small horse.");


//        w.censorSingleWordPalindromes(".,.,Anna,.. went to vote in the election to fulfil her tot-ally duty");
    }
}
