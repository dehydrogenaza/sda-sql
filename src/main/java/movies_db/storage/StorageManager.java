package movies_db.storage;
public class StorageManager {
    private IStorage storage;
    private static StorageType INITIAL_STORAGE = StorageType.IN_MEMORY;
    private StorageManager() {
//        storage = new InMemoryStorage();
        //storage = new HibernateStorage();
//        storage = new JDBCStorage();
        storage = INITIAL_STORAGE.createStorage();
    }

    private static class Holder {
        private static final StorageManager INSTANCE = new StorageManager();
    }

    private static StorageManager instance() {
        return Holder.INSTANCE;
    }

    public static IStorage getStorage() {
        return instance().storage;
    }

    public static void close() {
        instance().storage.close();
    }

    public static void setStorage(StorageType newStorage) {
        close();
        instance().storage = newStorage.createStorage();
    }

    public static void setInitialStorage(StorageType initialStorage) {
        INITIAL_STORAGE = initialStorage;
    }
}
