package kamil.szymoniak.view;

/**
 * Class is an object being sent as the session history attribute
 * 
 * @author Kamil Szymoniak
 * @version 4.0
 */
public class HistoryTable {
    /**
     * Whole table as a string containing HTML formatting
     */
    private String table;
    

    /**
     * Constructor
     * 
     * @param mode mode of operation
     * @param key key of the cipher
     * @param message input message to be encrypted
     * @param encryption encrypted message
     */
    public HistoryTable(String mode, String key, String message, String encryption) {

        table = "<tr>" +
                "<th>" +
                    mode +
                "</th>" +
                "<th>" +
                    key +
                "</th>" +
                "<th>" +
                    message +
                "</th>" +
                "<th>" +
                    encryption +
                "</th>" +
                "</tr>";
    }
    
    /**
     * Method overrides to string method
     * 
     * @return table as a string
     */
    @Override
    public String toString(){
        return table;
    }
    
    /**
     * Method adds a new row on top of the others.
     * 
     * @param mode mode of operation
     * @param key key of the cipher
     * @param message input message to be encrypted
     * @param encryption encrypted message
     */
    public void addRow(String mode, String key, String message, String encryption){
        table = "<tr>" +
                "<th>" +
                    mode +
                "</th>" +
                "<th>" +
                    key +
                "</th>" +
                "<th>" +
                    message +
                "</th>" +
                "<th>" +
                    encryption +
                "</th>" +
                "</tr>" +
                    table;
    }
}
