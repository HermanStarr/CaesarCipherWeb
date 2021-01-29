package kamil.szymoniak.model;

/**
 * Exception to be thrown when there are illegal arguments
 *
 * @author Kamil Szymoniak
 * @version 4.0
 */
public class IllegalInputException extends Exception{
    /**
     * Constructor for IllegalConsoleArgumentsException
     * 
     * @param s message of the exception
     */
    public IllegalInputException(String s) { super(s); }   
}
