package movies_db.actions;

abstract public class Action {
    private final int commandNumber;
    public Action(int commandNumber) {
        this.commandNumber = commandNumber;
    }
    abstract public boolean performThenContinue();
    public String getCommand() {
        return String.valueOf(commandNumber);
    }
    abstract public String getDescription();
}
