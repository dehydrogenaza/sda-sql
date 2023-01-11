package movies_db.actions;

import movies_db.storage.StorageType;
import movies_db.ui.Menu;

public class ChooseStorageAction extends Action {
    public ChooseStorageAction(int commandNumber) { super(commandNumber); }

    @Override
    public boolean performThenContinue() {
        Action chooseMemory = new SelectConcreteStorageAction(1, StorageType.IN_MEMORY);
        Action chooseDB = new SelectConcreteStorageAction(2, StorageType.DATABASE);
        Menu storageMenu = new Menu(chooseMemory, chooseDB);
        storageMenu.loop();
        return true;
    }

    @Override
    public String getDescription() {
        return "Zmień sposób zapisu";
    }
}
