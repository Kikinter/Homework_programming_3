import javax.swing.*;
import java.awt.*;

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
        int found = 0;
        int index = -1;
        if(option == 0){
            String name = JOptionPane.showInputDialog(new JFrame(), "Type in the name of the event you want to modify!");
            for(int i = 0; i < Main.events.size(); i++){
                if(Main.events.get(i).name.equals(name)) {
                    found++;
                    index = i;
                }
            }
        }
        //Date
        else {
            String date="";
            while (date.split("\\.").length != 3) {
                date = JOptionPane.showInputDialog(new JFrame(), "Type in the date of the event you want to modify!");
            }
            String[] parts = date.split("\\.");
            for(int i = 0; i < Main.events.size(); i++){
                if(Main.events.get(i).startDate.getYear() == Integer.parseInt(parts[0]) && Main.events.get(i).startDate.getMonthValue() == Integer.parseInt(parts[1]) && Main.events.get(i).startDate.getDayOfMonth() == Integer.parseInt(parts[2])) {
                    found++;
                    index = i;
                }
            }

        }
        if(index == -1) return null;
        return Main.events.get(index);
    }

}
