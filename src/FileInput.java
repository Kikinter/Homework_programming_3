import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileInput {
    FileInput(){
        try{
            String[] fileName;
            if(Main.lastChosen != null) fileName = Main.lastChosen.getName().split("\\.");
            else throw new Exception("No file was chosen");
            String extention = null;
            if(fileName.length == 2) extention = fileName[1];
            if(extention == null) throw new Exception("No extension was given");
            if(extention.equals("txt") || extention.equals("xml") || extention.equals("json")){
                UserFeedback feedback = new UserFeedback();
                if(!Main.changed || feedback.ask("Are you sure you don't want to save your changes?","Unsaved changes")){
                    switch (extention){
                        case "txt" -> readTxt(Main.lastChosen);
                        case "xml" -> readXml(Main.lastChosen);
                        case "json" -> readJson(Main.lastChosen);
                    }
                    openEvents();
                }
            }
            else throw new Exception("Wrong extension");
        } catch (Exception e){
            new ErrorMessage(e);
        }
    }

    private void readTxt(File file) throws Exception{
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object obj = ois.readObject();
            Main.events = (EventContainer) obj;
            ois.close();
        } catch (Exception e){
            throw new Exception("No such file");
        }

    }
    private void readXml(File file) throws Exception{
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(file);

            Element rootElement = document.getRootElement();
            List<Element> eventElements = rootElement.getChildren("event");

            EventContainer eventContainer = new EventContainer();

            for (Element eventElement : eventElements) {
                boolean favourite = Boolean.parseBoolean(eventElement.getChildText("favourite"));
                String name = eventElement.getChildText("name");
                String description = eventElement.getChildText("description");

                LocalDateTime startDate = LocalDateTime.parse(eventElement.getChildText("startDate"));
                LocalDateTime endDate = LocalDateTime.parse(eventElement.getChildText("endDate"));

                int daysBetween = Integer.parseInt(eventElement.getChildText("daysBetween"));

                // Assuming your XML structure includes an "icon" element for the image path
                String iconPath = eventElement.getChildText("icon");
                ImageIcon icon = new ImageIcon(iconPath);

                Event event = new Event(favourite, name, description, startDate, endDate, daysBetween, icon);
                eventContainer.add(event);
            }
            Main.events = eventContainer;
        } catch (Exception e){
            throw new Exception("No such file");
        }
    }
    private void readJson(File file) throws Exception{
        try {
            // Create a JsonReader from the file
            try (JsonReader jsonReader = Json.createReader(new FileReader(file))) {
                // Read the JSON structure
                JsonObject studentJson = jsonReader.readObject();
                JsonObject finalJson = studentJson.getJsonObject("student");
                JsonArray jsonArray = finalJson.getJsonArray("events");

                // Populate the EventContainer
                EventContainer eventContainer = new EventContainer();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject eventJson = jsonArray.getJsonObject(i);

                    boolean favourite = eventJson.getBoolean("favourite");
                    String name = eventJson.getString("name");
                    String description = eventJson.getString("description");
                    String startDateStr = eventJson.getString("startDate");
                    String endDateStr = eventJson.getString("endDate");
                    int daysBetween = eventJson.getInt("daysBetween");

                    // Parse start and end dates
                    LocalDateTime startDate = LocalDateTime.parse(startDateStr);
                    LocalDateTime endDate = LocalDateTime.parse(endDateStr);

                    // Assuming there's a method to retrieve the icon path from JSON
                    String iconPath = eventJson.getString("icon");

                    // Create the Event and add it to the container
                    Event event = new Event(favourite, name, description, startDate, endDate, daysBetween, new ImageIcon(iconPath));
                    eventContainer.add(event);
                }

                // Set the Main.events to the populated EventContainer
                Main.events = eventContainer;
            }

        } catch (IOException e) {
            throw new Exception("Error reading JSON file", e);
        }
    }
    private void openEvents(){
        ArrayList<Event> events = new ArrayList<>(Main.events.getCloseElement(Main.menu.days));
        if(!events.isEmpty())new OpenEvent(events, "Upcoming events");
        else {
            JOptionPane.showMessageDialog(null, "No upcoming events","Upcoming events", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
