import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class is for searching events.
 */
public class FindEvent {
    /**
     * This function finds the event based on either name or date.
     *
     * @return the found event
     */
    Event getEvent() {
        // Options for searching by name or date
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

        // List to store found events
        ArrayList<Event> found = new ArrayList<>();

        // Search by Name
        if (option == 0) {
            found.addAll(getName(Main.events.getEvents()));
        }
        // Search by Date
        else {
            found.addAll(getDate(Main.events.getEvents()));
        }

        // If only one event is found, return it
        if (found.size() == 1) return found.get(0);
            // If multiple events are found, let the user choose from them
        else if (found.size() > 1) {
            // Create an array of event names
            String[] eventNames = new String[found.size()];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            for (int i = 0; i < found.size(); i++) {
                eventNames[i] = found.get(i).name + ": " + found.get(i).startDate.format(formatter);
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

    /**
     * This function returns every event matching the name the user gave.
     *
     * @param events every event to search from
     * @return events that match the criteria
     */
    private ArrayList<Event> getName(ArrayList<Event> events) {
        ArrayList<Event> found = new ArrayList<>();
        // Get the name from the user
        String name = JOptionPane.showInputDialog(new JFrame(), "Type in the name of the event you want to modify!");
        // Search for events with the given name
        for (Event event : events) {
            if (event.name.equals(name)) {
                found.add(event);
            }
        }
        return found;
    }

    /**
     * This function returns every event matching the date the user gave.
     *
     * @param events every event to search from
     * @return events that match the criteria
     */
    private ArrayList<Event> getDate(ArrayList<Event> events) {
        String date = "";
        ArrayList<Event> found = new ArrayList<>();
        // Get the date from the user in the format YYYY.MM.DD
        while (date != null && date.split("\\.").length != 3) {
            date = JOptionPane.showInputDialog(new JFrame(), "Type in the date of the event you want to modify!(YYYY.MM.DD)");
        }
        // If a valid date is provided, search for events with the given date
        if (date != null) {
            String[] parts = date.split("\\.");
            for (Event event : events) {
                if (event.startDate.getYear() == Integer.parseInt(parts[0]) && event.startDate.getMonthValue() == Integer.parseInt(parts[1]) && event.startDate.getDayOfMonth() == Integer.parseInt(parts[2])) {
                    found.add(event);
                }
            }
            return found;
        } else {
            // Return an empty list if the user cancels the operation
            return new ArrayList<>();
        }
    }
}
