package movies_db.actions;

public class EndProgramAction extends Action {
    public EndProgramAction(int commandNumber) { super(commandNumber); }

    @Override
    public boolean performThenContinue() {
        System.out.println("KONIEC");
        return false;
    }

    @Override
    public String getDescription() {
        return "Zakończ program";
    }
}
