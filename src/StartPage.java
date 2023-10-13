import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage {
    StartPage(){
        JFrame frame = new JFrame("Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 800));
        JPanel buttons = new JPanel();
        buttons.setLayout(null);
        JButton fresh = new JButton("New calendar");
        fresh.setBounds(400,300,200,40);
        JButton old = new JButton("Load old calendar");
        old.setBounds(400,350,200,40);
        buttons.add(fresh);
        buttons.add(old);
        frame.getContentPane().add(buttons);
        frame.setJMenuBar(new JMenuBar());
        frame.setJMenuBar(Main.menu.menuBar);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        fresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CalendarMonthly();
            }
        });
        old.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(null);
                Main.lastChoosen = fileChooser.getSelectedFile();
            }
        });
    }


}
