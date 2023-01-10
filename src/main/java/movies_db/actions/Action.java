package movies_db.actions;

import movies_db.storage.IStorage;

abstract public class Action {
    protected final IStorage storage;
    public Action(IStorage storage) {
        this.storage = storage;
    }

    abstract public boolean performThenContinue();
    abstract public String getCommand();
}
