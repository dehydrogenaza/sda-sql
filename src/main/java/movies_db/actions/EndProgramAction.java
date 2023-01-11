package movies_db.actions;

import movies_db.ui.UI;

public class EndProgramAction extends Action {
    public EndProgramAction(int commandNumber) { super(commandNumber); }

    @Override
    public boolean performThenContinue() {
        UI.display("KONIEC");
//        System.out.println("KONIEC");
        return false;
    }

    @Override
    public String getDescription() {
        return "Zakończ program";
    }
}
