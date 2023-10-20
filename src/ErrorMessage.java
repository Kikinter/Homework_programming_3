import javax.swing.*;
public class ErrorMessage {
    ErrorMessage(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.INFORMATION_MESSAGE);
    }
}