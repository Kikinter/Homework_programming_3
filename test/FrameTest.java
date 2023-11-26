
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for StartPage, CalendarWeekly and EventActions
 */
public class FrameTest {

    /**
     * This method sets up a new container and sets the date to today before every test
     */
    @Before
    public void setUp(){
        Main.events = new EventContainer();
        Main.dateShown = LocalDateTime.now();
    }

    /**
     * This tests the start page's new calendar button
     */
    @Test
    public void testNewCalendarButtonPress() {
            StartPage startPage = new StartPage();
            JButton newCalendarButton = findButton(startPage, "New calendar");
            assertNotNull(newCalendarButton);
            newCalendarButton.doClick();
            assertTrue(Main.currentFrame instanceof CalendarMonthly);
            startPage.dispose();
    }
    /**
     * This tests the add button of event actions
     */
    @Test
    public void testAdd(){
        EventActions eventActions = new EventActions(EventActions.Type.ADD);
        JButton add = findButton(eventActions,"Add");
        assertNotNull(add);
        add.doClick();
        assertEquals(2,Main.events.size());
        eventActions.dispose();
    }
    /**
     * This tests the delete button of event actions
     */
    @Test
    public void testDelete(){
        EventActions eventActions = new EventActions(EventActions.Type.DELETE);
        JCheckBox checkBox = findCheckbox(eventActions);
        assertNotNull(checkBox);
        checkBox.setSelected(true);
        JButton delete = findButton(eventActions,"Delete");
        assertNotNull(delete);
        delete.doClick();
        assertEquals(0,Main.events.size());
    }
    /**
     * This tests the number of weekly view's buttons
     */
    @Test
    public void testNumberOfButtons(){
        CalendarWeekly weekly = new CalendarWeekly();
        int numberOfButtons = countButtons(weekly);
        //This has to be 9, because there are 7 buttons for the days and 2 for moving between dates
        assertEquals(9,numberOfButtons);
        weekly.dispose();
    }
    /**
     * This tests previous button of weekly view
     */
    @Test
    public void testPreviousButton(){
        CalendarWeekly calendarMonthly = new CalendarWeekly();
        ImageIcon icon = new ImageIcon("resources/images/left.png");
        JButton button = findButtonWithName(calendarMonthly, "previous");
        assertNotNull(button);
        button.doClick();
        LocalDate expectedDate = LocalDateTime.now().minusDays(LocalDateTime.now().getDayOfWeek().getValue() - 1 + 7).toLocalDate();
        assertEquals(expectedDate, Main.dateShown.toLocalDate());
    }
    /**
     * This tests next button of weekly view
     */
    @Test
    public void tesNextButton(){
        CalendarWeekly calendarMonthly = new CalendarWeekly();
        ImageIcon icon = new ImageIcon("resources/images/right.png");
        JButton button = findButtonWithName(calendarMonthly, "next");
        assertNotNull(button);
        button.doClick();
        LocalDate expectedDate = LocalDateTime.now().minusDays(LocalDateTime.now().getDayOfWeek().getValue() - 1).plusDays(7).toLocalDate();
        assertEquals(expectedDate, Main.dateShown.toLocalDate());
    }

    /**
     * This method gives back the first checkbox of the frame
     * @param container the frame where we want to find a checkbox
     * @return the first checkbox
     */
    private JCheckBox findCheckbox(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                return (JCheckBox) component;
            } else if (component instanceof Container) {
                JCheckBox checkBox = findCheckbox((Container) component);
                if (checkBox != null) {
                    return checkBox;
                }
            }
        }
        return null;
    }

    /**
     * This method finds the button with the given text
     * @param container the frame where we want to find the button
     * @param buttonText the text of the button
     * @return the found button
     */
    private JButton findButton(Container container, String buttonText) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(buttonText)) {
                    return button;
                }
            } else if (component instanceof Container) {
                JButton button = findButton((Container) component, buttonText);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }

    /**
     * This method returns the number of buttons on the frame
     * @param container the frame where we want to count the buttons
     * @return number of buttons
     */
    private int countButtons(Container container) {
        int buttonCount = 0;
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                buttonCount++;
            } else if (component instanceof Container) {
                buttonCount += countButtons((Container) component);
            }
        }
        return buttonCount;
    }
    /**
     * This method finds the button with the given name
     * @param container the frame where we want to find the button
     * @param name the name of the button
     * @return the found button
     */
    public static JButton findButtonWithName(Container container, String name) {
        Component[] components = container.getComponents();

        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getName() != null && button.getName().equals(name)) {
                    return button;
                }
            } else if (component instanceof Container) {
                // If the component is a container, recursively search inside it
                JButton button = findButtonWithName((Container) component, name);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }
}
