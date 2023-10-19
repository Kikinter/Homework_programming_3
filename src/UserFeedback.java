import javax.swing.*;
public class UserFeedback {
    boolean ask(){
        int confirm = JOptionPane.showOptionDialog(
                null,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null);
        return confirm == 0;
    }
}
