package christmas.model.domain.event.config;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventConfig {

    public static final int YEAR = 2023;
    public static final int MONTH = 12;
    public static final int START_DATE = 1;
    public static final int END_DATE = 31;
    public static final int CHRISTMAS = 25;
    private static List<LocalDate> dates;

    private static final EventConfig instance = new EventConfig();

    private EventConfig() {
        generateDates();
    }

    public static EventConfig getInstance() {
        return instance;
    }

    private static void generateDates() {
        dates = new ArrayList<>();
        LocalDate date = LocalDate.of(YEAR, MONTH, START_DATE);
        while (date.getMonthValue() == MONTH) {
            dates.add(date);
            date = date.plusDays(1);
        }
    }

    public List<LocalDate> weekdayDate() {
        return dates.stream()
                .filter(isWeekDay())
                .collect(Collectors.toList());
    }

    private Predicate<LocalDate> isWeekDay() {
        return date -> date.getMonthValue() == MONTH &&
                date.getDayOfWeek() != DayOfWeek.FRIDAY &&
                date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    public List<LocalDate> weekendDate() {
        return dates.stream()
                .filter(isWeekendDay())
                .collect(Collectors.toList());
    }

    private Predicate<LocalDate> isWeekendDay() {
        return date -> date.getMonthValue() == MONTH &&
                date.getDayOfWeek() == DayOfWeek.FRIDAY ||
                date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public List<LocalDate> specialDate() {
        return dates.stream()
                .filter(isSpecialDay())
                .collect(Collectors.toList());
    }

    private Predicate<LocalDate> isSpecialDay() {
        return date -> date.getMonthValue() == MONTH &&
                date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                date.getDayOfMonth() == CHRISTMAS;
    }

    public LocalDate christmasDate() {
        return LocalDate.of(YEAR, MONTH, CHRISTMAS);
    }

}
