import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;

public class Date {
    Year year;
    Month month;
    int day;
    int hour;
    int minute;
    Date(Year year, Month month, int day, int hour, int minute)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
    public Date(LocalDate localDate) {
        this.year = Year.of(localDate.getYear());
        this.month = localDate.getMonth();
        this.day = localDate.getDayOfMonth();
        this.hour = 0;
        this.minute = 0;
    }
    static int daysBetween(Date d1, Date d2){
        LocalDate localDate1 = LocalDate.of(d1.year.getValue(), d1.month, d1.day);
        LocalDate localDate2 = LocalDate.of(d2.year.getValue(), d2.month, d2.day);
        long daysDifference = ChronoUnit.DAYS.between(localDate1, localDate2);
        return ((int) daysDifference);
    }

    public String currentDay(Date d) {
        LocalDate localDate = LocalDate.of(d.year.getValue(), d.month, d.day);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.toString();
    }
}
