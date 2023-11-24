

import javax.swing.*;
import java.time.DayOfWeek;
public class Menu extends JMenuBar{
    DayOfWeek daySelected = DayOfWeek.MONDAY;
    int days = 7;
    Menu(){
        JMenu file = new JMenu("File");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        file.add(load);
        file.add(save);
        load.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            Main.lastChosen = fileChooser.getSelectedFile();
            if(!Main.changed){
                new FileInput();
            }
            else {
                if(new UserFeedback().ask()) new FileInput();
            }
        });
        save.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            Main.lastChosen = fileChooser.getSelectedFile();
            if(Main.events.size() == 0){
                new ErrorMessage(new Exception("No events to save"));
            }
            else {
                new FileOutput();
            }
        });

        this.add(file);
        JMenu view = new JMenu("View");
        JMenuItem month = new JMenuItem("Monthly");
        JMenuItem week = new JMenuItem("Weekly");
        view.add(month);
        view.add(week);
        month.addActionListener(e -> {
            Main.currentFrame.dispose();
            new CalendarMonthly();
        });
        week.addActionListener(e -> {
            Main.currentFrame.dispose();
            new CalendarWeekly();
        });
        this.add(view);
        JMenu help = new JMenu("Help");
        JMenuItem web = new JMenuItem("How to use");
        help.add(web);
        this.add(help);
        JMenu settings = getSettings();
        this.add(settings);

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

    private JMenu getSettings() {
        JMenu settings = new JMenu("Settings");
        JSpinner daysCounter = new JSpinner(new SpinnerNumberModel(7,0,14,1));
        settings.add(daysCounter);
        daysCounter.addChangeListener(e -> this.days = (int)daysCounter.getValue());
        JMenu startDay = new JMenu("First day of the week");
        for(int i = 0; i < 7; i++){
            JMenuItem menuItem = new JMenuItem(DayOfWeek.of(i + 1).name());
            startDay.add(menuItem);
            menuItem.addActionListener(e -> {
                Main.menu.daySelected = DayOfWeek.valueOf(menuItem.getText());
                Main.currentFrame.dispose();
                if(Main.currentFrame instanceof CalendarWeekly){
                    Main.currentFrame = new CalendarWeekly();
                } else Main.currentFrame = new CalendarMonthly();
            });
        }
        settings.add(startDay);
        return settings;
    }
}
