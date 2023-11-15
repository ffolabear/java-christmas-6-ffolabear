package christmas.model.domain;

import christmas.model.domain.constant.Badge;
import christmas.model.domain.constant.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.constant.SystemMessage;
import christmas.view.validation.DateValidator;
import christmas.view.validation.MenuValidator;
import christmas.view.validation.UserInputUtil;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;

public class Customer {

    private final InputView<String, LocalDate> dateInputView = new InputView<>(new UserInputUtil(),
            new DateValidator());
    private final InputView<String, EnumMap<Menu, List<Integer>>> menuInputView = new InputView<>(new UserInputUtil(),
            new MenuValidator());
    private Order order;
    private Badge badge;

    public void inputDetail() {
        OutputView outputView = new OutputView();
        outputView.printSystemMessage(SystemMessage.INPUT_DATE, 12);
        LocalDate localDate = inputVisitDate();
        outputView.printSystemMessage(SystemMessage.INPUT_MENU);
        generateOrder(localDate);
    }

    private LocalDate inputVisitDate() {
        return dateInputView.read().data();
    }

    private void generateOrder(LocalDate visitDate) {
        EnumMap<Menu, List<Integer>> orderMenu = menuInputView.read().data();
        this.order = new Order(visitDate, orderMenu);
    }

    public Order getOrder() {
        return order;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
}
