import javax.swing.*;

/**
 * This class is to display simple error messages
 */
public class ErrorMessage {
    /**
     * This sets up a simple option pane to inform the user about the error
     * @param e the exception that occurred
     */
    ErrorMessage(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
    }
}