package movies_db.actions;

import movies_db.storage.IStorage;

public class EndProgramAction extends Action {
    public EndProgramAction(IStorage storage) {
        super(storage);
    }

    @Override
    public boolean performThenContinue() {
        System.out.println("KONIEC");
        return false;
    }

    @Override
    public String getCommand() {
        return "3";
    }
}
