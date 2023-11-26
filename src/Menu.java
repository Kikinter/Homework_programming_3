import javax.swing.*;
import java.time.DayOfWeek;

/**
 * This class represents the menu displayed on almost every frame.
 */
public class Menu extends JMenuBar {
    /**
     * First day of the week, monday by default
     */
    DayOfWeek daySelected = DayOfWeek.MONDAY;
    /**
     * Number of days which counts as close
     */
    int days = 7;

    /**
     * Constructor to set up the entire menu.
     */
    Menu() {
        // File menu with load and save options
        JMenu file = new JMenu("File");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        file.add(load);
        file.add(save);

        // ActionListener for loading events
        load.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            Main.lastChosen = fileChooser.getSelectedFile();
            new FileInput();
            Main.currentFrame.dispose();
            Main.currentFrame = new CalendarMonthly();
        });

        // ActionListener for saving events
        save.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            Main.lastChosen = fileChooser.getSelectedFile();
            if (Main.events.size() == 0) {
                new ErrorMessage(new Exception("No events to save"));
            } else {
                new FileOutput();
            }
        });

        this.add(file);

        // View menu with options for monthly and weekly views
        JMenu view = new JMenu("View");
        JMenuItem month = new JMenuItem("Monthly");
        JMenuItem week = new JMenuItem("Weekly");
        view.add(month);
        view.add(week);

        // ActionListener for switching to monthly view
        month.addActionListener(e -> {
            Main.currentFrame.dispose();
            new CalendarMonthly();
        });

        // ActionListener for switching to weekly view
        week.addActionListener(e -> {
            Main.currentFrame.dispose();
            new CalendarWeekly();
        });

        this.add(view);

        // Settings menu
        JMenu settings = getSettings();
        this.add(settings);

        // Event menu with options for adding, deleting, and modifying events
        JMenu event = new JMenu("Event");
        JMenuItem add = new JMenuItem("Add");
        add.addActionListener(e -> new EventActions(EventActions.Type.ADD));
        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(e -> new EventActions(EventActions.Type.DELETE));
        JMenuItem modify = new JMenuItem("Modify");
        modify.addActionListener(e -> new EventActions(EventActions.Type.MODIFY));
        this.add(event);
        event.add(add);
        event.add(delete);
        event.add(modify);
    }

    /**
     * Set up the settings part of the menu.
     *
     * @return the settings menu
     */
    private JMenu getSettings() {
        JMenu settings = new JMenu("Settings");

        // Spinner for changing the number of days displayed
        JSpinner daysCounter = new JSpinner(new SpinnerNumberModel(7, 0, 14, 1));
        settings.add(daysCounter);
        daysCounter.addChangeListener(e -> this.days = (int) daysCounter.getValue());

        // Menu for selecting the first day of the week
        JMenu startDay = new JMenu("First day of the week");
        for (int i = 0; i < 7; i++) {
            JMenuItem menuItem = new JMenuItem(DayOfWeek.of(i + 1).name());
            startDay.add(menuItem);

            // ActionListener for changing the first day of the week
            menuItem.addActionListener(e -> {
                Main.menu.daySelected = DayOfWeek.valueOf(menuItem.getText());
                Main.currentFrame.dispose();
                if (Main.currentFrame instanceof CalendarWeekly) {
                    Main.currentFrame = new CalendarWeekly();
                } else Main.currentFrame = new CalendarMonthly();
            });
        }

        settings.add(startDay);
        return settings;
    }
}
