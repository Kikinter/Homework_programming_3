import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OpenEvent extends JFrame{
    OpenEvent(LocalDateTime date){
        //Setup frame
        this.setTitle("Events");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 600));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        //Scroll panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel(new FlowLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Already existing events
        ImageIcon favourite = new ImageIcon("resources/images/star_icon.png");
        JTextArea textArea;
        ArrayList<JPanel> panels = new ArrayList<>();
        ArrayList<Event> events = Main.events.contains(date);
        if (!events.isEmpty()) {
            for (Event event : events) {
                JPanel smallPanel = new JPanel(new BorderLayout());
                JLabel fav = new JLabel(favourite);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.hh.dd HH:mm");
                String formattedStartDate = event.startDate.format(formatter);
                String formattedEndDate = event.endDate.format(formatter);
                String labelText = event.name + ": " + event.description + "\nfrom: " + formattedStartDate + " to: " + formattedEndDate;
                textArea = new JTextArea(labelText);
                textArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, textArea.getPreferredSize().height));
                smallPanel.add(textArea);
                if(event.icon != null) smallPanel.add(new JLabel(event.icon));
                if(event.favourite) smallPanel.add(fav);
                panels.add(smallPanel);
            }
        }
        for(JPanel pan : panels){
            panel.add(pan);
        }

        

        //Frame visible
        scrollPane.setViewportView(panel);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
