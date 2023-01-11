package movies_db.actions;

import movies_db.storage.*;

public class SelectConcreteStorageAction extends Action {
    private final StorageType storageType;
    public SelectConcreteStorageAction(int commandNumber, StorageType storageType) {
        super(commandNumber);
        this.storageType = storageType;
    }

    @Override
    public boolean performThenContinue() {
        if (StorageManager.getStorage().getClass().getSimpleName().equals(storageType.className)) {
            System.out.println("(wybrano obecnie używaną metodę zapisu, brak zmian)");
        } else {
            StorageManager.setStorage(storageType.createStorage());
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Wybierz: " + storageType;
    }
}
