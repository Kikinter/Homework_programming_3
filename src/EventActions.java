import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class EventActions extends JFrame {
    enum Type {
        ADD,
        DELETE,
        MODIFY
    }

    private File iconFile = null;

    EventActions(Type type) {
        this.setTitle(type.name());
        this.setPreferredSize(new Dimension(1000, 400));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        boolean emptycontainer = Main.events.size() == 0;

        switch (type) {
            case ADD -> {
                JTextField name = new JTextField("Name of the event");
                JTextArea description = new JTextArea("Add description");
                JCheckBox favourite = new JCheckBox();
                JSpinner repeat = new JSpinner(new SpinnerNumberModel(0, 0, 365, 1));
                JButton icon = new JButton("Icon");
                icon.addActionListener(e -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpeg", "png"));
                    int returnValue = fileChooser.showSaveDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) iconFile = fileChooser.getSelectedFile();
                });

                JButton finish = new JButton("Add");
                finish.addActionListener(e -> {
                    Icon iconImage = null;
                    if (iconFile != null) iconImage = new ImageIcon(iconFile.getAbsolutePath());
                    Main.events.add(new Event(favourite.isSelected(), name.getText(), description.getText(), null, null, (int) repeat.getValue(), iconImage));
                });
                panel.add(name);
                panel.add(description);
                panel.add(favourite);
                panel.add(repeat);
                panel.add(icon);
                panel.add(finish);
                if (emptycontainer) {
                    this.add(panel);
                    this.pack();
                    this.setLocationRelativeTo(null);
                    this.setVisible(true);
                }
            }
            case DELETE -> {
                if (emptycontainer) {
                    JOptionPane.showMessageDialog(this, "No events to be deleted.");
                } else {
                    JCheckBox[] checkBoxes = new JCheckBox[Main.events.size()];
                    for (int i = 0; i < Main.events.size(); i++) {
                        checkBoxes[i] = new JCheckBox(Main.events.get(i).name);
                        panel.add(checkBoxes[i]);
                    }
                    JButton finish = new JButton("Delete");
                    finish.addActionListener(e -> {
                        ArrayList<Event> delete = new ArrayList<>();
                        for (int i = 0; i < Main.events.size(); i++) {
                            if (checkBoxes[i].isSelected()) delete.add(Main.events.get(i));
                        }
                        Main.events.removeAll(delete);
                        this.dispose();
                    });
                    panel.add(finish, BorderLayout.SOUTH);
                }
            }
            case MODIFY -> {
                if (emptycontainer) {
                    JOptionPane.showMessageDialog(this, "No events to be modified.");
                } else {
                    Event change = new FindEvent().getEvent();
                    if(change != null) {
                        JTextField name = new JTextField(change.name);
                        JTextArea description = new JTextArea(change.description);
                        JCheckBox favourite = new JCheckBox();
                        JSpinner repeat = new JSpinner(new SpinnerNumberModel(change.daysBetween, 0, 365, 1));
                        JButton icon = new JButton(change.icon);
                        icon.addActionListener(e -> {
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpeg", "png"));
                            int returnValue = fileChooser.showSaveDialog(null);
                            if (returnValue == JFileChooser.APPROVE_OPTION) iconFile = fileChooser.getSelectedFile();
                        });

                        JButton finish = new JButton("Modify");
                        finish.addActionListener(e -> {
                            Icon iconImage = null;
                            if (iconFile != null) iconImage = new ImageIcon(iconFile.getAbsolutePath());
                            Main.events.get(Main.events.getIndex(change)).change(favourite.isSelected(), name.getText(), description.getText(), null, null, (int) repeat.getValue(), iconImage);
                        });
                        panel.add(name);
                        panel.add(description);
                        panel.add(favourite);
                        panel.add(repeat);
                        panel.add(icon);
                        panel.add(finish);
                    }
                    else{
                        JLabel text = new JLabel("No events match your search conditions.");
                        panel.add(text);
                    }
                }
            }
        }
        if(!emptycontainer) {
            this.add(panel);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }
}
