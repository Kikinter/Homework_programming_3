import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;

/**
 * This class is for the monthly view
 */
public class CalendarMonthly extends JFrame {

    /**
     * The constructor sets up the whole frame
     */
    CalendarMonthly() {
        // Setting the initial date shown to the current month
        Main.dateShown = LocalDateTime.now().minusDays(LocalDateTime.now().getDayOfMonth() - (long) 1);

        // Setting the icon for the frame
        this.setIconImage(new ImageIcon("resources/images/calendar.png").getImage());

        // Frame, panel, menu setup
        this.setTitle("Calendar");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handling window closing event
                if (Main.changed) {
                    UserFeedback userFeedback = new UserFeedback();
                    boolean confirm = userFeedback.ask("Are you sure you want to exit?", "Exit Confirmation");
                    if (confirm) System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        });
        this.setPreferredSize(new Dimension(714, 761));
        this.setJMenuBar(new JMenuBar());
        this.setJMenuBar(Main.menu);

        // Creating and configuring the main panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Day labels set, stored, and added to the panel
        ArrayList<JLabel> nameOfDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            nameOfDays.add(new JLabel(Main.menu.daySelected.plus(i).name()));
            nameOfDays.get(i).setBounds(20 + 100 * i, 40, 100, 30);
        }
        for (JLabel label : nameOfDays) {
            panel.add(label);
        }

        // Label for the year+month
        JLabel date = new JLabel(Main.dateShown.getYear() + " " + Main.dateShown.getMonth().name());
        date.setBounds(310, 5, 120, 30);
        panel.add(date);

        // Calculating offset for the first day displayed
        LocalDateTime start = Main.dateShown;
        int offset = 0;
        if (DayOfWeek.from(start) != Main.menu.daySelected) {
            while (DayOfWeek.from(start) != Main.menu.daySelected) {
                start = start.minusDays(1);
                offset++;
            }
        }

        // Calculating forward offset for the last day displayed
        LocalDateTime end = Main.dateShown.plusMonths(1).minusDays(1);
        int forwardOffset = 0;
        if (end.getDayOfWeek() != Main.menu.daySelected.plus(6)) {
            while (DayOfWeek.from(end) != Main.menu.daySelected.plus(6)) {
                end = end.plusDays(1);
                forwardOffset++;
            }
        }

        // Buttons for days with numbers on them first set
        ArrayList<JButton> days = new ArrayList<>();
        int lines = 0;
        int breakLine = 0;
        LocalDateTime first = start;
        for (int i = 0; i < Main.dateShown.getMonth().length(Year.of(Main.dateShown.getYear()).isLeap()) + offset + forwardOffset; i++) {
            String num = Integer.toString(start.plusDays(i).getDayOfMonth());
            days.add(new JButton(num));
            if (i % 7 == 0) {
                lines++;
                breakLine = 0;
            }
            days.get(i).setBounds(breakLine * 100, lines * 100, 100, 100);
            int difference = i;
            days.get(i).addActionListener(e -> new OpenEvent(first.plusDays(difference)));
            breakLine++;
        }
        for (JButton b : days) {
            panel.add(b);
        }

        // Buttons to go forward and backward in date set and added to panel
        JButton backDate = getWayButton(date, days, panel, false);
        JButton forwardDate = getWayButton(date, days, panel, true);
        ImageIcon back = new ImageIcon(new ImageIcon("resources/images/left.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        ImageIcon forward = new ImageIcon(new ImageIcon("resources/images/right.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        backDate.setIcon(back);
        forwardDate.setIcon(forward);
        panel.add(backDate);
        panel.add(forwardDate);

        // Making the frame visible
        this.getContentPane().add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Main.currentFrame = this;
    }


    /**
     * This function return the forward or backward button
     * @param date currently displayed date
     * @param days the new days button, after the returned button was pressed
     * @param panel the panel for the buttons/labels
     * @param forward if true it creates a forward button, if not it creates a backward button
     * @return the button created for changing between dates
     */
    private static JButton getWayButton(JLabel date, ArrayList<JButton> days, JPanel panel, boolean forward) {
        Icon back = new ImageIcon();
        JButton moveButton = new JButton(back);
        if (!forward) moveButton.setBounds(15, 5, 50, 35);
        else moveButton.setBounds(637, 5, 50, 35);
        moveButton.addActionListener(e -> {
            // Handling button click to move forward or backward in date
            if (!forward) Main.dateShown = Main.dateShown.minusMonths(1);
            else Main.dateShown = Main.dateShown.plusMonths(1);
            date.setText(Main.dateShown.getYear() + " " + Main.dateShown.getMonth().name());
            for (JButton button : days) {
                panel.remove(button);
            }
            days.clear();

            // Calculating offsets for the first Monday and last Sunday
            LocalDateTime firstMonday = Main.dateShown;
            int off = 0;
            if (DayOfWeek.from(firstMonday) != Main.menu.daySelected) {
                while (DayOfWeek.from(firstMonday) != Main.menu.daySelected) {
                    firstMonday = firstMonday.minusDays(1);
                    off++;
                }
            }

            LocalDateTime end = Main.dateShown.plusMonths(1).minusDays(1);
            int forwardOffset = 0;
            if (end.getDayOfWeek() != Main.menu.daySelected.plus(6)) {
                while (DayOfWeek.from(end) != Main.menu.daySelected.plus(6)) {
                    end = end.plusDays(1);
                    forwardOffset++;
                }
            }

            int line = 0;
            int lineBreak = 0;
            LocalDateTime starter = firstMonday;
            for (int i = 0; i < Main.dateShown.getMonth().length(Year.of(Main.dateShown.getYear()).isLeap()) + off + forwardOffset; i++) {
                String num = Integer.toString(firstMonday.plusDays(i).getDayOfMonth());
                days.add(new JButton(num));
                if (i % 7 == 0) {
                    line++;
                    lineBreak = 0;
                }
                days.get(i).setBounds(lineBreak * 100, line * 100, 100, 100);
                int difference = i;
                days.get(i).addActionListener(k -> new OpenEvent(starter.plusDays(difference)));
                lineBreak++;
            }
            for (JButton b : days) {
                panel.add(b);
            }
            panel.repaint();
        });
        return moveButton;
    }
}
