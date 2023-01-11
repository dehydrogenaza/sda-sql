package movies_db.storage;

public enum StorageType {
    DATABASE("DBStorage"),
    IN_MEMORY("InMemoryStorage");

    public final String className;
    StorageType(String className) {
        this.className = className;
    }

    public IStorage createStorage() {
        IStorage storage = null;
        switch (this) {
            case DATABASE -> storage = new DBStorage();
            case IN_MEMORY -> storage = new InMemoryStorage();
        }
        return storage;
    }
}
