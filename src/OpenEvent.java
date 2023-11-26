import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class is for displaying events on a certain date or a set of events.
 */
public class OpenEvent extends JFrame {

    /**
     * Constructor to display events on a specific date.
     *
     * @param date the date of the event
     */
    OpenEvent(LocalDateTime date) {
        // Call the main constructor with events on the specified date and a default title
        new OpenEvent(Main.events.contains(date), "Events");
    }

    /**
     * Constructor to set up the frame for displaying a list of events.
     *
     * @param events the events to display
     * @param title  the title of the frame
     */
    OpenEvent(ArrayList<Event> events, String title) {
        // Set up frame properties
        this.setIconImage(new ImageIcon("resources/images/calendar.png").getImage());
        this.setTitle(title);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 600));
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        // Create a scroll pane for displaying events
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ArrayList<JPanel> panels = new ArrayList<>();

        // Define icons for favorites and non-favorites
        ImageIcon favourite = new ImageIcon(new ImageIcon("resources/images/star_icon.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        ImageIcon noFavourite = new ImageIcon(new ImageIcon("resources/images/empty_star.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));

        // Create JTextArea for displaying event details in each panel
        JTextArea textArea;

        // Check if there are events to display
        if (!events.isEmpty()) {
            for (Event event : events) {
                JPanel jPanel = new JPanel(new BorderLayout());
                JLabel fav;
                // Set favorite icon based on the event's favorite status
                if (event.favourite) fav = new JLabel(favourite);
                else fav = new JLabel(noFavourite);

                // Set event icon
                JLabel icon = new JLabel(new ImageIcon(new ImageIcon(event.iconPath).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));

                // Format start and end dates for display
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.hh.dd HH:mm");
                String formattedStartDate = event.startDate.format(formatter);
                String formattedEndDate = event.endDate.format(formatter);

                // Create formatted text for JTextArea
                String labelText = event.name + ": " + event.description + "\nfrom: " + formattedStartDate + " to: " + formattedEndDate;

                // Set up JTextArea
                textArea = new JTextArea(labelText);
                textArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, textArea.getPreferredSize().height));
                textArea.setEditable(false);
                textArea.setSize(new Dimension(200, 150));

                // Add components to the panel
                jPanel.add(textArea, BorderLayout.WEST);
                jPanel.add(icon, BorderLayout.CENTER);
                jPanel.add(fav, BorderLayout.EAST);
                panels.add(jPanel);
            }
        }

        // Create a panel with a GridLayout for arranging event panels
        JPanel panel = new JPanel(new GridLayout(Math.max(panels.size(), 8), 1));

        // Add event panels to the main panel
        for (JPanel p : panels) {
            panel.add(p);
        }

        // Make the frame visible
        scrollPane.setViewportView(panel);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
