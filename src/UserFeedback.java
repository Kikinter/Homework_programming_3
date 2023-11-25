import javax.swing.*;
public class UserFeedback {
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
