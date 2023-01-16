package movies_db.storage;

public enum StorageType {
    IN_MEMORY("InMemoryStorage"),
    JDBC("JDBCStorage"),
    HIBERNATE("HibernateStorage");

    public final String storageClassName;
    StorageType(String storageClassName) {
        this.storageClassName = storageClassName;
    }

    public IStorage createStorage() {
        IStorage storage = null;
        switch (this) {
            case JDBC -> storage = new JDBCStorage();
            case IN_MEMORY -> storage = new InMemoryStorage();
            case HIBERNATE -> storage = new HibernateStorage();
        }
        return storage;
    }
}
