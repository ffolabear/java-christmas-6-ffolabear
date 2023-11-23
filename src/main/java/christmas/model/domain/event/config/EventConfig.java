package christmas.model.domain.event.config;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EventConfig {

    public static final int YEAR = 2023;
    public static final int MONTH = 12;
    public static final int START_DATE = 1;
    public static final int END_DATE = 31;
    public static final LocalDate CHRISTMAS = LocalDate.of(YEAR, MONTH, 25);
    private static List<LocalDate> decemberDate;

    private static final EventConfig instance = new EventConfig();

    private EventConfig() {
        generateDates();
    }

    public static EventConfig getInstance() {
        return instance;
    }

    private static void generateDates() {
        decemberDate = new ArrayList<>();
        LocalDate startDate = LocalDate.of(YEAR, MONTH, START_DATE);
        LocalDate endDate = LocalDate.of(YEAR, MONTH, END_DATE);
        decemberDate = startDate.datesUntil(endDate).toList();
    }

    public List<LocalDate> weekdayDate() {
        return decemberDate.stream()
                .filter(isWeekDay())
                .toList();
    }

    private Predicate<LocalDate> isWeekDay() {
        return date -> date.getMonthValue() == MONTH &&
                date.getDayOfWeek() != DayOfWeek.FRIDAY &&
                date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    public List<LocalDate> weekendDate() {
        return decemberDate.stream()
                .filter(isWeekendDay())
                .toList();
    }

    private Predicate<LocalDate> isWeekendDay() {
        return date -> date.getMonthValue() == MONTH &&
                date.getDayOfWeek() == DayOfWeek.FRIDAY ||
                date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public List<LocalDate> specialDate() {
        return decemberDate.stream()
                .filter(isSpecialDay())
                .toList();
    }

    private Predicate<LocalDate> isSpecialDay() {
        return date -> date.getMonthValue() == MONTH &&
                date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                date.getDayOfMonth() == CHRISTMAS.getDayOfMonth();
    }

}
