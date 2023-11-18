import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        boolean success = true;
        JPanel mainPanel = new JPanel();
        switch (type) {
            case ADD -> setupAdd(emptyContainer,mainPanel);
            case DELETE -> setupDelete(panel,emptyContainer);
            case MODIFY -> success = setupModify(emptyContainer,mainPanel);
        }
        if(!emptyContainer && success) {
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }
    private void setupAdd(boolean emptyContainer, JPanel mainPanel){
        setUpPanel(mainPanel,null);
        if (emptyContainer) {
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            for (int i = 0; i < Main.events.size(); i++) {
                checkBoxes[i] = new JCheckBox(Main.events.get(i).name + " " + Main.events.get(i).startDate.format(formatter));
                panel.add(checkBoxes[i]);
            }
            JButton finish = new JButton("Delete");
            finish.addActionListener(e -> {
                ArrayList<Event> delete = new ArrayList<>();
                for (int i = 0; i < Main.events.size(); i++) {
                    if (checkBoxes[i].isSelected()) {
                        delete.add(Main.events.get(i));

                    }
                }
                Main.events.removeAll(delete);
                this.dispose();
            });
            panel.add(finish, BorderLayout.SOUTH);
            this.add(panel);
        }
    }

    private boolean setupModify(boolean emptyContainer, JPanel mainPanel){
        if (emptyContainer) {
            JOptionPane.showMessageDialog(this, "No events to be modified.");
            return false;
        } else {
                Event change = new FindEvent().getEvent();
                if(change != null) {
                    setUpPanel(mainPanel,change);
                    return true;
                }
                else {
                    JOptionPane.showMessageDialog(this, "No events to be modified.");
                    return false;
                }
        }
    }
    private void setUpPanel(JPanel mainPanel, Event event){
        String name,description,buttonText;
        int yearStart,monthStart,dayStart,hourStart,minuteStart, yearEnd,monthEnd,dayEnd,hourEnd,minuteEnd,repeatNumber;
        boolean selected;
        Icon img;
        if(event == null){
            name = "Name of the event";
            description = "Description";
            yearStart = LocalDateTime.now().getYear();
            monthStart = LocalDateTime.now().getMonthValue();
            dayStart = LocalDateTime.now().getDayOfMonth();
            hourStart = 12;
            minuteStart = 30;
            yearEnd = yearStart;
            monthEnd = monthStart;
            dayEnd = dayStart;
            hourEnd = hourStart;
            minuteEnd = minuteStart;
            repeatNumber = 0;
            buttonText = "Add";
            selected = false;
            img = null;
        } else {
            name = event.name;
            description = event.description;
            yearStart = event.startDate.getYear();
            monthStart = event.startDate.getMonthValue();
            dayStart = event.startDate.getDayOfMonth();
            hourStart = event.startDate.getHour();
            minuteStart = event.startDate.getMinute();
            yearEnd = event.endDate.getYear();
            monthEnd = event.endDate.getMonthValue();
            dayEnd = event.endDate.getDayOfMonth();
            hourEnd = event.endDate.getHour();
            minuteEnd = event.endDate.getMinute();
            repeatNumber = event.daysBetween;
            buttonText = "Modify";
            selected = true;
            img = event.icon;
        }



        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        //Name
        JTextField nameField = new JTextField(name);
        panel1.add(nameField);
        //Description
        JTextArea descriptionField = new JTextArea(description);
        panel2.add(descriptionField);
        //StartDate and EndDate
        panel3.setLayout(new FlowLayout());
        //Start
        String y = "Year :", m = "Month: ", d = "Day: ", h = "Hour: ", mi = "Minute: ";

        panel3.add(new JLabel("START DATE:"));
        panel3.add(new JLabel(y));
        JSpinner startYear = new JSpinner(new SpinnerNumberModel(yearStart,1900,2100,1));
        panel3.add(startYear);
        panel3.add(new JLabel(m));
        JSpinner startMonth = new JSpinner(new SpinnerNumberModel(monthStart,1,12,1));
        panel3.add(startMonth);
        panel3.add(new JLabel(d));
        JSpinner startDay = new JSpinner(new SpinnerNumberModel(dayStart,1,31,1));
        panel3.add(startDay);
        panel3.add(new JLabel(h));
        JSpinner startHour = new JSpinner(new SpinnerNumberModel(hourStart,0,24,1));
        panel3.add(startHour);
        panel3.add(new JLabel(mi));
        JSpinner startMinute = new JSpinner(new SpinnerNumberModel(minuteStart,0,60,1));
        panel3.add(startMinute);
        //End
        panel4.add(new JLabel("END DATE:"));
        panel4.add(new JLabel(y));
        JSpinner endYear = new JSpinner(new SpinnerNumberModel(yearEnd,1900,2100,1));
        panel4.add(endYear);
        panel4.add(new JLabel(m));
        JSpinner endMonth = new JSpinner(new SpinnerNumberModel(monthEnd,1,12,1));
        panel4.add(endMonth);
        panel4.add(new JLabel(d));
        JSpinner endDay = new JSpinner(new SpinnerNumberModel(dayEnd,1,31,1));
        panel4.add(endDay);
        panel4.add(new JLabel(h));
        JSpinner endHour = new JSpinner(new SpinnerNumberModel(hourEnd,0,24,1));
        panel4.add(endHour);
        panel4.add(new JLabel(mi));
        JSpinner endMinute = new JSpinner(new SpinnerNumberModel(minuteEnd,0,60,1));
        panel4.add(endMinute);


        JCheckBox favourite = new JCheckBox();
        favourite.setSelected(selected);
        JSpinner repeat = new JSpinner(new SpinnerNumberModel(repeatNumber, 0, 365, 1));
        JButton icon = new JButton("Icon");
        JLabel image = new JLabel(img);
        icon.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpeg", "png"));
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) iconFile = fileChooser.getSelectedFile();
        });

        JButton finish = new JButton(buttonText);
        finish.addActionListener(e -> {
            if(!Main.events.duplicate(nameField.getText(),(int)startYear.getValue(),(int)startMonth.getValue(),(int)startDay.getValue())) {
                if (event == null) {
                    Icon iconImage = null;
                    LocalDateTime startDate = LocalDateTime.of((int) startYear.getValue(), (int) startMonth.getValue(), (int) startDay.getValue(), (int) startHour.getValue(), (int) startMinute.getValue());
                    LocalDateTime endDate = LocalDateTime.of((int) endYear.getValue(), (int) endMonth.getValue(), (int) endDay.getValue(), (int) endHour.getValue(), (int) endMinute.getValue());
                    if (iconFile != null) iconImage = new ImageIcon(iconFile.getAbsolutePath());
                    Main.events.add(new Event(favourite.isSelected(), nameField.getText(), descriptionField.getText(), startDate, endDate, (int) repeat.getValue(), iconImage));
                } else {
                    Main.events.remove(event);
                    LocalDateTime startDate = LocalDateTime.of((int) startYear.getValue(), (int) startMonth.getValue(), (int) startDay.getValue(), (int) startHour.getValue(), (int) startMinute.getValue());
                    LocalDateTime endDate = LocalDateTime.of((int) endYear.getValue(), (int) endMonth.getValue(), (int) endDay.getValue(), (int) endHour.getValue(), (int) endMinute.getValue());
                    event.name = nameField.getText();
                    event.description = descriptionField.getText();
                    event.startDate = startDate;
                    event.endDate = endDate;
                    event.favourite = favourite.isSelected();
                    event.daysBetween = (int) repeat.getValue();
                    if (iconFile != null) event.icon = new ImageIcon(iconFile.getAbsolutePath());
                    Main.events.add(event);
                }
            } else {
                JOptionPane.showMessageDialog(this, "The event you want to save already exists", "Duplicate", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panel5.add(new JLabel("Favourite"));
        panel5.add(favourite);
        panel5.add(new JLabel("How many days before it repeats"));
        panel5.add(repeat);
        panel5.add(icon);
        panel5.add(image);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);
        mainPanel.add(finish);
        this.add(mainPanel);
    }

}
