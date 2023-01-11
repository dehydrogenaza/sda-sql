package movies_db.ui;

import movies_db.actions.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Menu {
    private final Map<String, Action> menuActions;

    public Menu(Action... actions) {
        menuActions = Arrays.stream(actions).collect(Collectors.toMap(Action::getCommand, action -> action));
    }

    public void loop() {
        do { } while (selectMenuAction());
    }

    private boolean selectMenuAction() {
        String input = UI.ask(getOptionsList());

        if (menuActions.containsKey(input)) {
            Action action = menuActions.get(input);
            return action.performThenContinue();
        }

        UI.display("NIE MA TAKIEJ KOMENDY");
        return true;
    }

    private String getOptionsList() {
        StringBuilder sb = new StringBuilder();
        menuActions.forEach((k, v) -> sb.append(k)
                .append(". ")
                .append(v.getDescription())
                .append("\n"));
        return sb.toString();
    }
}
