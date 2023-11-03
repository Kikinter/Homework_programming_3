import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OpenEvent extends JFrame{
    OpenEvent(LocalDateTime date){
        //Setup frame
        this.setTitle("Events");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(300, 800));
        this.setLayout(new BorderLayout());

        //Scroll panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Already existing events
        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<Event> events = Main.events.contains(date);
        if (!events.isEmpty()) {
            for (Event event : events) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" HH:mm");
                String formattedStartDate = event.startDate.format(formatter);
                String formattedEndDate = event.endDate.format(formatter);
                String labelText = "<html>" + event.name + ": " + event.description +
                        "<br>from: " + formattedStartDate + " to: " + formattedEndDate + "</html>";
                JLabel label = new JLabel(labelText);
                label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
                labels.add(label);
            }
        }
        for(JLabel label : labels){
            panel.add(label);
        }

        

        //Frame visible
        scrollPane.setViewportView(panel);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
