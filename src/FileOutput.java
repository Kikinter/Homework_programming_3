import jakarta.json.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.*;

/**
 * This class is for writing into files.
 */
public class FileOutput {
    /**
     * The constructor sets up the file output.
     */
    FileOutput() {
        try {
            String[] fileName;
            if (Main.lastChosen != null) fileName = Main.lastChosen.getName().split("\\.");
            else throw new Exception("No file was chosen");
            String extension = null;
            if (fileName.length == 2) extension = fileName[1];
            if (extension.equals("txt") || extension.equals("xml") || extension.equals("json")) {
                switch (extension) {
                    case "txt" -> writeTxt(Main.lastChosen);
                    case "xml" -> writeXml(Main.lastChosen);
                    case "json" -> writeJson(Main.lastChosen);
                }
                Main.changed = false;
            } else throw new Exception("Wrong type of extension");
        } catch (Exception e) {
            new ErrorMessage(e);
        }
    }

    /**
     * This function is for writing into a txt file.
     *
     * @param file the file to read from
     * @throws Exception the error occurred during the file writing
     */
    private void writeTxt(File file) throws Exception {
        try {
            file.getParentFile().mkdirs();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(Main.events);
            oos.close();
        } catch (Exception e) {
            throw new Exception("There was a problem during the writing");
        }
    }

    /**
     * This function is for writing into an xml file.
     *
     * @param filename the file to read from
     * @throws Exception the error occurred during the file writing
     */
    private void writeXml(File filename) throws Exception {
        Element rootElement = new Element("events");
        Document document = new Document(rootElement);

        EventContainer eventContainer = Main.events;
        for (Event event : eventContainer.getEvents()) {
            Element eventElement = new Element("event");

            eventElement.addContent(new Element("favourite").setText(String.valueOf(event.favourite)));
            eventElement.addContent(new Element("name").setText(event.name));
            eventElement.addContent(new Element("description").setText(event.description));
            eventElement.addContent(new Element("startDate").setText(event.startDate.toString()));
            eventElement.addContent(new Element("endDate").setText(event.endDate.toString()));
            eventElement.addContent(new Element("daysBetween").setText(String.valueOf(event.daysBetween)));
            if (event.iconPath != null) eventElement.addContent(new Element("icon").setText(event.iconPath));
            rootElement.addContent(eventElement);
        }

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            xmlOutputter.output(document, fos);
        } catch (IOException e) {
            throw new Exception("There was a problem during the writing");
        }
    }

    /**
     * This function is for writing into a json file.
     *
     * @param filename the file to read from
     * @throws Exception the error occurred during the file writing
     */
    private void writeJson(File filename) throws Exception {
        try {
            EventContainer eventContainer = Main.events;

            // Create a JSON array builder for events
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (Event event : eventContainer.getEvents()) {
                // Build JSON object for each event
                JsonObjectBuilder eventBuilder = Json.createObjectBuilder()
                        .add("favourite", event.favourite)
                        .add("name", event.name)
                        .add("description", event.description)
                        .add("startDate", event.startDate.toString())
                        .add("endDate", event.endDate.toString())
                        .add("daysBetween", event.daysBetween);

                if (event.iconPath != null) eventBuilder.add("icon", event.iconPath);
                // Add the event object to the array
                jsonArrayBuilder.add(eventBuilder);
            }

            // Create the final JSON array
            JsonObjectBuilder finalJson = Json.createObjectBuilder()
                    .add("events", jsonArrayBuilder);

            // Build the student JSON object
            JsonObject studentJson = Json.createObjectBuilder()
                    .add("student", finalJson.build())
                    .build();

            // Write the JSON to the file
            try (FileWriter fileWriter = new FileWriter(filename)) {
                JsonWriter jsonWriter = Json.createWriter(fileWriter);
                jsonWriter.writeObject(studentJson);
                jsonWriter.close();
            }
        } catch (IOException e) {
            throw new Exception("There was a problem during the writing");
        }
    }
}
