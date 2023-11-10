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
        boolean emptyContainer = Main.events.size() == 0;

        switch (type) {
            case ADD -> setupAdd(panel,emptyContainer);
            case DELETE -> setupDelete(panel,emptyContainer);
            case MODIFY -> setupModify(panel,emptyContainer);
        }
        if(!emptyContainer) {
            this.add(panel);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }
    private void setupAdd(JPanel panel, boolean emptyContainer){
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
        if (emptyContainer) {
            this.add(panel);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }

    private void setupDelete(JPanel panel, boolean emptyContainer){
        if (emptyContainer) {
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

    private void setupModify(JPanel panel, boolean emptyContainer){
        if (emptyContainer) {
            JOptionPane.showMessageDialog(this, "No events to be modified.");
        } else {
            try {
                Event change = new FindEvent().getEvent();
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
            catch (Exception e){
                JLabel text = new JLabel(e.getMessage());
                panel.setLayout(new FlowLayout(FlowLayout.CENTER));
                panel.add(text,BorderLayout.CENTER);
            }
        }
    }

}
