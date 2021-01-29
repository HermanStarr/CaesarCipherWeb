package kamil.szymoniak.model;
import java.util.*;

/**
 * CipherModel class of Caesar cipher
 * 
 * @author Kamil Szymoniak
 * @version 4.0
 */
public class CipherModel {

    /**
     * Default constructor for Model
     */
    public CipherModel() {
    }
    
    /**
     * Method transforms console arguments to a single string from position 2
     * 
     * @param args console arguments
     * @return unified console arguments
     * @throws IllegalMessageFormException if input is incorrect
     */
    private String getAppendedString(List<String> args) throws IllegalMessageFormException{
        StringBuilder sb = new StringBuilder();
        for (String word : checkInputForm(args)) {
            sb.append(word);
            sb.append(" ");
        }
        return sb.substring(0, sb.toString().length() - 1);
    }
    
    /**
     * Method checks input to find illegal characters
     * 
     * @param input String containing message to be processed
     * @return true if input is correct
     * @throws IllegalMessageFormException if input is incorrect
     */
    private List<String> checkInputForm(List<String> input) throws IllegalMessageFormException{       
        input.remove(0);
        input.remove(0);
        if(!input.stream().allMatch(word->word.matches("^[a-zA-Z ]*$")))
            throw new IllegalMessageFormException();
        return input;
    }
    
    /**
     * Method encodes or decodes a message passed to the program
     * 
     * @param input a message to be encoded or decoded
     * @param key an integral value being the alphabetical shift distance of the code
     * @return encoded or decoded message
     * @throws IllegalMessageFormException if input is incorrect
     */
    public String translate(List<String> input, int key) throws IllegalMessageFormException {
        
        char[] inputArray = getAppendedString(input).toCharArray();
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == 32) {
            }
            else {
                inputArray[i] =
                        inputArray[i] > 96 ?
                            (char) ((key + inputArray[i] - 97) % 26 + 97) :
                            (char) ((key + inputArray[i] - 65) % 26 + 65)
                ;
            }
        }
        return new String(inputArray);
    }
    
}
