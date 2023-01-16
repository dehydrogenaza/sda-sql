package movies_db.actions;

import movies_db.storage.*;
import movies_db.ui.UI;

public class SelectConcreteStorageAction extends Action {
    private final StorageType storageType;
    public SelectConcreteStorageAction(int commandNumber, StorageType storageType) {
        super(commandNumber);
        this.storageType = storageType;
    }

    @Override
    public boolean performThenContinue() {
        if (StorageManager.getStorage().getClass().getSimpleName().equals(storageType.storageClassName)) {
            UI.display("(wybrano obecnie używaną metodę zapisu, brak zmian)");
        } else {
            StorageManager.setStorage(storageType);
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Wybierz: " + storageType;
    }
}
