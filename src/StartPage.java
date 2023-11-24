import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartPage extends JFrame{
    StartPage(){
        this.setTitle("Calendar");
        this.setIconImage(new ImageIcon("resources/images/calendar.png").getImage());
        this.getContentPane().setBackground(Color.cyan);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(Main.changed) {
                    UserFeedback userFeedback = new UserFeedback();
                    boolean confirm = userFeedback.ask();
                    if (confirm) System.exit(0);
                }
                else System.exit(0);
            }
        });
        this.setPreferredSize(new Dimension(1000, 800));
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setJMenuBar(new JMenuBar());
        this.setJMenuBar(Main.menu);
        JButton fresh = new JButton("New calendar");
        fresh.setBounds(400,300,200,40);
        JButton old = new JButton("Load old calendar");
        old.setBounds(400,350,200,40);
        panel.add(fresh);
        panel.add(old);
        ImageIcon background = new ImageIcon("resources/images/calendar_background.png");
        //Frame visible
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        //Action listeners for buttons in the middle
        fresh.addActionListener(e -> {
            Main.currentFrame.dispose();
            new CalendarMonthly();
        });
        old.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            Main.lastChosen = fileChooser.getSelectedFile();
            new FileInput();
        });
        Main.currentFrame = this;
    }


}
