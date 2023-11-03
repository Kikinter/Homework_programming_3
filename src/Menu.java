import javax.swing.*;
public class Menu {
    JMenuBar menuBar;
    Menu(){
        menuBar = new JMenuBar();
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

        menuBar.add(file);
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
        menuBar.add(view);
        JMenu help = new JMenu("Help");
        JMenuItem web = new JMenuItem("How to use");
        help.add(web);
        menuBar.add(help);
        new JMenu("Settings");
    }

}
