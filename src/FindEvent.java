import javax.swing.*;
import java.util.ArrayList;

public class FindEvent{
    Event getEvent() throws Exception{
        Object[] options = {"Name", "Date"};
        int option = JOptionPane.showOptionDialog(
                null,
                "What do you want to search by?",
                "Search condition",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);

        //Name
        ArrayList<Event> found = new ArrayList<>();
        if(option == 0){
            String name = JOptionPane.showInputDialog(new JFrame(), "Type in the name of the event you want to modify!");
            for(int i = 0; i < Main.events.size(); i++){
                if(Main.events.get(i).name.equals(name)) {
                    found.add(Main.events.get(i));
                }
            }
        }
        //Date
        else {
            String date="";
            while (date != null && date.split("\\.").length != 3) {
                date = JOptionPane.showInputDialog(new JFrame(), "Type in the date of the event you want to modify!(YYYY.MM.DD)");
            }
            if(date != null) {
                String[] parts = date.split("\\.");
                for (int i = 0; i < Main.events.size(); i++) {
                    if (Main.events.get(i).startDate.getYear() == Integer.parseInt(parts[0]) && Main.events.get(i).startDate.getMonthValue() == Integer.parseInt(parts[1]) && Main.events.get(i).startDate.getDayOfMonth() == Integer.parseInt(parts[2])) {
                        found.add(Main.events.get(i));
                    }
                }
            }
            else throw new Exception("Nothing was given to find event!");
        }
        if(found.size() == 1) return found.get(0);
        //Select from found events
        else if(found.size() > 1){
            // Create an array of event names
            String[] eventNames = new String[found.size()];
            for (int i = 0; i < found.size(); i++) {
                eventNames[i] = found.get(i).name + ": "+ found.get(i).startDate; // Assuming you have a getName() method in your Event class
            }
            // Show a JOptionPane with a JComboBox to select the event
            String selectedEvent = (String) JOptionPane.showInputDialog(
                    null,
                    "Select an event:",
                    "Select Event",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    eventNames,
                    eventNames[0]
            );

            // Find the selected event
            for (Event event : found) {
                if (event.name.equals(selectedEvent)) {
                    return event;
                }
            }
        }
        return null;
    }

}
