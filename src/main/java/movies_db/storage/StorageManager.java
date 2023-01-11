package movies_db.storage;
public class StorageManager {
    private IStorage storage;
    private StorageManager() {
        storage = new InMemoryStorage();
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

    public static void setStorage(IStorage newStorage) {
        close();
        instance().storage = newStorage;
    }
}
