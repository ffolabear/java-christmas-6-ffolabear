package christmas.view.constant;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.InputUtil;

public class UserInputUtil implements InputUtil {

    @Override
    public String read() {
        return Console.readLine();
    }

}