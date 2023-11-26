import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class represents the opening frame of the calendar application.
 */
public class StartPage extends JFrame {
    /**
     * Constructor for setting up the frame.
     */
    StartPage() {
        // Set frame properties
        this.setTitle("Calendar");
        this.setIconImage(new ImageIcon("resources/images/calendar.png").getImage());
        this.getContentPane().setBackground(Color.cyan);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // Add window listener for close operation
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Check if there are unsaved changes before closing
                if (Main.changed) {
                    UserFeedback userFeedback = new UserFeedback();
                    // Ask for confirmation before exiting
                    boolean confirm = userFeedback.ask("Are you sure you want to exit?", "Exit Confirmation");
                    if (confirm) System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        });

        // Set frame dimensions
        this.setPreferredSize(new Dimension(1000, 800));

        // Create a panel with null layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Set the menu bar
        this.setJMenuBar(new JMenuBar());
        this.setJMenuBar(Main.menu);

        // Create buttons for new and old calendars
        JButton fresh = new JButton("New calendar");
        fresh.setBounds(400, 300, 200, 40);
        JButton old = new JButton("Load old calendar");
        old.setBounds(400, 350, 200, 40);

        // Add buttons to the panel
        panel.add(fresh);
        panel.add(old);

        // Add the panel to the frame
        this.add(panel);

        // Pack and display the frame
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Action listeners for buttons in the middle
        fresh.addActionListener(e -> {
            // Dispose of the current frame and create a new monthly calendar frame
            Main.currentFrame.dispose();
            new CalendarMonthly();
        });
        old.addActionListener(e -> {
            // Open file chooser for loading an old calendar
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            Main.lastChosen = fileChooser.getSelectedFile();

            // Load the file and create a new monthly calendar frame
            new FileInput();
            Main.currentFrame.dispose();
            Main.currentFrame = new CalendarMonthly();
        });

        // Set the current frame to this instance
        Main.currentFrame = this;
    }
}
