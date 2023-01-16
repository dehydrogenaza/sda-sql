package movies_db.storage;

import movies_db.movie.Genre;
import movies_db.movie.Movie;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCStorage extends GenericDBStorage implements IStorage {
    private static final String ADD_MOVIE_QUERY_TEMPLATE =
            "INSERT INTO movies(title, productionYear, genre, rating) VALUES (?,?,?,?);";
    private final Connection conn;

    public JDBCStorage() {
        super();
        String password = getDBPassword();
        try {
            conn = establishDBConnection(password);
            createTableSql();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Movie m) {
        try {
            PreparedStatement ps = conn.prepareStatement(ADD_MOVIE_QUERY_TEMPLATE);
            ps.setString(1, m.getTitle());
            ps.setInt(2, m.getProductionYear());
            ps.setString(3, m.getGenre().toString());
            ps.setDouble(4, m.getRating());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayAll() {
        try {
            ResultSet movies = readMovies();
            displayResults(movies);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableSql() throws SQLException {
        String createTableSql = """
                    CREATE TABLE IF NOT EXISTS movies(
                    id int AUTO_INCREMENT NOT NULL,
                    title varchar(255) NOT NULL,
                    productionYear int NOT NULL,
                    genre varchar(255) NOT NULL,
                    rating double NOT NULL,
                    PRIMARY KEY (id)
                    );
                    """;
        conn.createStatement().execute(createTableSql);
    }

    private ResultSet readMovies() throws SQLException {
        String selectAllSql = "SELECT * FROM movies";
        return conn.createStatement().executeQuery(selectAllSql);
    }

    private void displayResults(ResultSet movieResults) throws SQLException {
        List<Movie> moviesList = new LinkedList<>();
        while (movieResults.next()) {
            String title = movieResults.getString("title");
            int productionYear = movieResults.getInt("productionYear");
            String genre = movieResults.getString("genre");
            double rating = movieResults.getDouble("rating");

            Movie movie = new Movie.Builder()
                    .withTitle(title)
                    .withProductionYear(productionYear)
                    .withGenre(Genre.valueOf(genre))
                    .withRating(rating)
                    .create();

            moviesList.add(movie);
        }
        concatenateResultsAndDisplay(moviesList);
    }

    private Connection establishDBConnection(String password) throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, password);
    }
}
