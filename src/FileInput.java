import javax.swing.*;
import java.util.ArrayList;

public class FileInput {
    FileInput(){
        try{
            String[] fileName = Main.lastChosen.getName().split("\\.");
            String extention = null;
            if(fileName.length == 2) extention = fileName[1];
            if(extention.equals("txt") || extention.equals("xml") || extention.equals("json")){
                UserFeedback feedback = new UserFeedback();
                if(!Main.changed || feedback.ask()){
                    switch (extention){
                        case "txt":{
                            break;
                        }
                        case "xml":{
                            break;
                        }
                        case "json":{
                            break;
                        }
                    }
                }

                //Reminder
                JFrame frame = new JFrame("Upcoming events");
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                JPanel panel = new JPanel();
                ArrayList<Event> events = Main.events.getCloseElement(Main.menu.days);
            }
            else throw new Exception("Wrong extension");
        } catch (Exception e){
            new ErrorMessage(e);
        }
    }

    private void readTxt(){

    }
    private void readXml(){

    }
    private void readJson(){

    }

}
