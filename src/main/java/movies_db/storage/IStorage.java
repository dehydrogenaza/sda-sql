package movies_db.storage;

import movies_db.movie.Movie;

public interface IStorage {
    void add(Movie m);
    void displayAll();
    void close();
}
