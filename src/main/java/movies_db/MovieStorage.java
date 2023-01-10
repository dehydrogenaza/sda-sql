package movies_db;

import java.util.ArrayList;
import java.util.List;

public class MovieStorage implements IStorage {
    private final List<Movie> movies = new ArrayList<>();

    @Override
    public void add(Movie f) {
        movies.add(f);
    }

    @Override
    public void displayAll() {
        movies.forEach(System.out::println);
    }
}
