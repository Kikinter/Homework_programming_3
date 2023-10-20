import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OpenEvent {
    OpenEvent(LocalDateTime date){
        //Setup frame
        JFrame frame = new JFrame("Events");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 800));
        frame.setLayout(new BorderLayout());

        //Scroll panel
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Already existing events
        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<Event> events = Main.events.contains(date);
        if(!events.isEmpty()){
            for(Event event : events){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" HH:mm");
                String formattedStartDate = event.startDate.format(formatter);
                String formattedEndDate = event.endDate.format(formatter);
                labels.add(new JLabel(event.name + ":" + event.description + " from:" + formattedStartDate + " to:" + formattedEndDate));
            }
        }
        for(JLabel label : labels){
            panel.add(label);
        }

        //Frame visible
        scrollPane.setViewportView(panel);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
