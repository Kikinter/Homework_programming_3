
import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

//This class has test for StartPage, CalendarWeekly and EventActions as well
public class FrameTest {
    @Before
    public void setUp(){
        Main.events = new EventContainer();
        Main.dateShown = LocalDateTime.now();
    }
    @Test
    public void testNewCalendarButtonPress() {
            StartPage startPage = new StartPage();
            JButton newCalendarButton = findButton(startPage, "New calendar");
            assertNotNull(newCalendarButton);
            newCalendarButton.doClick();
            assertTrue(Main.currentFrame instanceof CalendarMonthly);
            startPage.dispose();
    }
    @Test
    public void testAdd(){
        EventActions eventActions = new EventActions(EventActions.Type.ADD);
        JButton add = findButton(eventActions,"Add");
        assertNotNull(add);
        add.doClick();
        assertEquals(2,Main.events.size());
        eventActions.dispose();
    }
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
    @Test
    public void testNumberOfButtons(){
        CalendarWeekly weekly = new CalendarWeekly();
        int numberOfButtons = countButtons(weekly);
        //This has to be 9, because there are 7 buttons for the days and 2 for moving between dates
        assertEquals(9,numberOfButtons);
        weekly.dispose();
    }
    @Test
    public void testPreviousButton(){
        CalendarWeekly calendarMonthly = new CalendarWeekly();
        ImageIcon icon = new ImageIcon("resources/images/left.png");
        JButton button = findButtonByIconSource(calendarMonthly, icon);
        assertNotNull(button);
        button.doClick();
        LocalDate expectedDate = LocalDateTime.now().minusDays(LocalDateTime.now().getDayOfWeek().getValue() - 1 + 7).toLocalDate();
        assertEquals(expectedDate, Main.dateShown.toLocalDate());
    }
    @Test
    public void tesNextButton(){
        CalendarWeekly calendarMonthly = new CalendarWeekly();
        ImageIcon icon = new ImageIcon("resources/images/right.png");
        JButton button = findButtonByIconSource(calendarMonthly, icon);
        assertNotNull(button);
        button.doClick();
        LocalDate expectedDate = LocalDateTime.now().minusDays(LocalDateTime.now().getDayOfWeek().getValue() - 1).plusDays(7).toLocalDate();
        assertEquals(expectedDate, Main.dateShown.toLocalDate());
    }

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
    private JButton findButtonByIconSource(Container container, Icon targetIcon) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (iconsHaveSameSource(button.getIcon(), targetIcon)) {
                    return button;
                }
            } else if (component instanceof Container) {
                JButton button = findButtonByIconSource((Container) component, targetIcon);
                if (button != null) {
                    return button;
                }
            }
        }
        return null;
    }

    private boolean iconsHaveSameSource(Icon icon1, Icon icon2) {
        if (icon1 == null && icon2 == null) {
            return true;
        }
        if (icon1 == null || icon2 == null) {
            return false;
        }
        if (icon1 instanceof ImageIcon imageIcon1 && icon2 instanceof ImageIcon imageIcon2) {
            return imageIcon1.getImage().getSource().equals(imageIcon2.getImage().getSource());
        }
       return false;
    }

}
