package movies_db.actions;

import movies_db.storage.IStorage;

public class DisplayMoviesAction extends Action {
    public DisplayMoviesAction(IStorage storage) {
        super(storage);
    }

    @Override
    public boolean performThenContinue() {
        storage.displayAll();
        return true;
    }

    @Override
    public String getCommand() {
        return "2";
    }
}
