package movies_db.storage;

import movies_db.movie.Movie;
import movies_db.ui.UI;

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
        StringBuilder moviesMsg = new StringBuilder();
        movies.forEach(m -> moviesMsg.append(m).append("\n"));
        UI.display(moviesMsg);
    }

    @Override
    public void close() {
        movies.clear();
    }
}
