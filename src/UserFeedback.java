import javax.swing.*;

/**
 * This class is to get feedback
 */
public class UserFeedback {
    /**
     * This function ask the user and returns the response
     * @param message message of the option pane
     * @param title title of the option pane
     * @return true if the user agrees, false otherwise
     */
    boolean ask(String message, String title){
        int confirm = JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null);
        return confirm == 0;
    }
}
