package movies_db.actions;

import movies_db.storage.StorageManager;

public class DisplayMoviesAction extends Action {
    public DisplayMoviesAction(int commandNumber) { super(commandNumber); }

    @Override
    public boolean performThenContinue() {
        StorageManager.getStorage().displayAll();
        return true;
    }

    @Override
    public String getDescription() {
        return "Wyświetl filmy";
    }
}
