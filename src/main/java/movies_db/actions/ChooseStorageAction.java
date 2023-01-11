package movies_db.actions;

import movies_db.storage.StorageType;
import movies_db.ui.Menu;

public class ChooseStorageAction extends Action {
    public ChooseStorageAction(int commandNumber) { super(commandNumber); }

    @Override
    public boolean performThenContinue() {
        StorageType[] values = StorageType.values();
        Action[] storageActions = new Action[values.length];
        for (int i = 0; i < values.length; i++) {
            StorageType type = values[i];
            storageActions[i] = new SelectConcreteStorageAction(i + 1, type);
        }

        Menu storageMenu = new Menu(storageActions);
        storageMenu.loop();
        return true;
    }

    @Override
    public String getDescription() {
        return "Zmień sposób zapisu";
    }
}
