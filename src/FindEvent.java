import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FindEvent{
    Event getEvent(){
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
            found.addAll(getName(Main.events.getEvents()));
        }
        //Date
        else {
            found.addAll(getDate(Main.events.getEvents()));
        }
        if(found.size() == 1) return found.get(0);
        //Select from found events
        else if(found.size() > 1){
            // Create an array of event names
            String[] eventNames = new String[found.size()];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            for (int i = 0; i < found.size(); i++) {
                eventNames[i] = found.get(i).name + ": "+ found.get(i).startDate.format(formatter);
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
                String name = event.name + ": " + event.startDate.format(formatter);
                if (name.equals(selectedEvent)) {
                    return event;
                }
            }
        }
        return null;
    }

    private ArrayList<Event> getName(ArrayList<Event> events){
        ArrayList<Event> found = new ArrayList<>();
        String name = JOptionPane.showInputDialog(new JFrame(), "Type in the name of the event you want to modify!");
        for (Event event : events) {
            if (event.name.equals(name)) {
                found.add(event);
            }
        }
        return found;
    }
    private ArrayList<Event> getDate(ArrayList<Event> events){
        String date="";
        ArrayList<Event> found = new ArrayList<>();
        while (date != null && date.split("\\.").length != 3) {
            date = JOptionPane.showInputDialog(new JFrame(), "Type in the date of the event you want to modify!(YYYY.MM.DD)");
        }
        if(date != null) {
            String[] parts = date.split("\\.");
            for (Event event : events) {
                if (event.startDate.getYear() == Integer.parseInt(parts[0]) && event.startDate.getMonthValue() == Integer.parseInt(parts[1]) && event.startDate.getDayOfMonth() == Integer.parseInt(parts[2])) {
                    found.add(event);
                }
            }
            return found;
        }
        else return new ArrayList<>();
    }

}
