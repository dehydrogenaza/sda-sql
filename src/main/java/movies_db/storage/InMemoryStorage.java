package movies_db.storage;

import movies_db.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStorage implements IStorage {
    private final List<Movie> movies = new ArrayList<>();

    @Override
    public void add(Movie f) {
        movies.add(f);
    }

    @Override
    public void displayAll() {
        concatenateResultsAndDisplay(movies);
    }

    @Override
    public void close() {
        movies.clear();
    }
}
