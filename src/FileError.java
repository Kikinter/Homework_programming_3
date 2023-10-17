import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileError {
    FileError(Exception e){
        JFrame error = new JFrame("Error");
        JButton ok = new JButton("Ok");
        ok.setBounds(120, 50, 50, 35);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                error.dispose();
            }
        });
        error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        error.setPreferredSize(new Dimension(300, 150));
        JLabel text = new JLabel();
        text.setText(e.getMessage());
        JPanel p = new JPanel();
        p.setLayout(null);
        p.add(ok);
        p.add(text);
        text.setBounds(135, 20, 160, 30);
        error.getContentPane().add(p);
        error.pack();
        error.setLocationRelativeTo(null);
        error.setVisible(true);
    }
}
