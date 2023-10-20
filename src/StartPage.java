import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartPage {
    StartPage(){
        JFrame frame = new JFrame("Calendar");
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(Main.changed) {
                    UserFeedback userFeedback = new UserFeedback();
                    boolean confirm = userFeedback.ask();
                    if (confirm) frame.dispose();
                }
                else frame.dispose();
            }
        });
        frame.setPreferredSize(new Dimension(1000, 800));
        JPanel buttons = new JPanel();
        buttons.setLayout(null);
        frame.setJMenuBar(new JMenuBar());
        frame.setJMenuBar(Main.menu.menuBar);
        JButton fresh = new JButton("New calendar");
        fresh.setBounds(400,300,200,40);
        JButton old = new JButton("Load old calendar");
        old.setBounds(400,350,200,40);
        buttons.add(fresh);
        buttons.add(old);

        //Frame visible
        frame.getContentPane().add(buttons);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        //Action listeners for buttons in the middle
        fresh.addActionListener(e -> {
            frame.dispose();
            new CalendarMonthly();
        });
        old.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            Main.lastChosen = fileChooser.getSelectedFile();
            new FileInput();
        });
        Main.currentFrame = frame;
    }


}
