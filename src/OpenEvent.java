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
        this.setPreferredSize(new Dimension(500, 600));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        //Scroll panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Already existing events
        ArrayList<JTextArea> textAreas = new ArrayList<>();
        ArrayList<Event> events = Main.events.contains(date);
        if (!events.isEmpty()) {
            for (Event event : events) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" HH:mm");
                String formattedStartDate = event.startDate.format(formatter);
                String formattedEndDate = event.endDate.format(formatter);
                String labelText = event.name + ": " + event.description +
                        "\nfrom: " + formattedStartDate + " to: " + formattedEndDate;
                JTextArea textArea = new JTextArea(labelText);
                textArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, textArea.getPreferredSize().height));
                textAreas.add(textArea);
            }
        }
        for(JTextArea textArea : textAreas){
            panel.add(textArea);
        }

        

        //Frame visible
        scrollPane.setViewportView(panel);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
