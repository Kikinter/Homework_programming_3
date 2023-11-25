import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OpenEvent extends JFrame{
    OpenEvent(LocalDateTime date){
        ArrayList<Event> events = Main.events.contains(date);
        new OpenEvent(events,"Events");
    }
    OpenEvent(ArrayList<Event> events, String title){
        //Setup frame
        this.setIconImage(new ImageIcon("resources/images/calendar.png").getImage());
        this.setTitle(title);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 600));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        //Scroll panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ArrayList<JPanel> panels = new ArrayList<>();

        //Already existing events
        ImageIcon favourite = new ImageIcon(new ImageIcon("resources/images/star_icon.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        ImageIcon noFavourite = new ImageIcon(new ImageIcon("resources/images/empty_star.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JTextArea textArea;

        if (!events.isEmpty()) {
            for (Event event : events) {
                JPanel jPanel = new JPanel(new BorderLayout());
                JLabel fav;
                if(event.favourite) fav = new JLabel(favourite);
                else fav = new JLabel(noFavourite);
                JLabel icon = new JLabel(new ImageIcon(new ImageIcon(event.iconPath).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.hh.dd HH:mm");
                String formattedStartDate = event.startDate.format(formatter);
                String formattedEndDate = event.endDate.format(formatter);
                String labelText = event.name + ": " + event.description + "\nfrom: " + formattedStartDate + " to: " + formattedEndDate;
                textArea = new JTextArea(labelText);
                textArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, textArea.getPreferredSize().height));
                textArea.setEditable(false);
                textArea.setSize(new Dimension(200,150));
                jPanel.add(textArea,BorderLayout.WEST);
                jPanel.add(icon,BorderLayout.CENTER);
                jPanel.add(fav,BorderLayout.EAST);
                panels.add(jPanel);
            }
        }

        JPanel panel = new JPanel(new GridLayout(Math.max(panels.size(), 8),1));
        for(JPanel p : panels){
            panel.add(p);
        }


        //Frame visible
        scrollPane.setViewportView(panel);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
