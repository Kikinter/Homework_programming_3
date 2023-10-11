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
        menuBar.add(file);
        JMenu view = new JMenu("View");
        JMenuItem month = new JMenuItem("Montly");
        JMenuItem week = new JMenuItem("Weekly");
        JCheckBox favourites = new JCheckBox("Show favourites");
        view.add(month);
        view.add(week);
        view.add(favourites);
        menuBar.add(view);
        JMenu help = new JMenu("Help");
        JMenuItem web = new JMenuItem("How to use");
        help.add(web);
        menuBar.add(help);
    }
    JMenuBar getMenuBar(){
        return menuBar;
    }

}
