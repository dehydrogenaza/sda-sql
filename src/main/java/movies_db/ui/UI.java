package movies_db.ui;
public class UI {
    private IUserInterface ui;
    private UI() {
        ui = new ConsoleUI();
    }

    private static class Holder {
        private static final UI INSTANCE = new UI();
    }

    private static UI instance() { return Holder.INSTANCE; }

    public static void display(Object msg) {
        instance().ui.display(msg.toString());
    }

    public static String ask(String msg) {
        return instance().ui.ask(msg);
    }

    public static void setUI(IUserInterface newUI) {
        instance().ui = newUI;
    }
}
