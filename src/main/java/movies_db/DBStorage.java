package movies_db;

import java.sql.*;
import java.util.Scanner;

public class DBStorage implements IStorage {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/movies";
    private static final String DATABASE_LOGIN = "root";
    private static final String ADD_MOVIE_QUERY_TEMPLATE =
            "INSERT INTO movies(title, productionYear, genre, rating) VALUES (?,?,?,?);";
    private final Connection conn;

    public DBStorage() {
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

    private void displayResults(ResultSet movies) throws SQLException {
        while (movies.next()) {
            //int id = movies.getInt("id");
            String title = movies.getString("title");
            int productionYear = movies.getInt("productionYear");
            String genre = movies.getString("genre");
            double rating = movies.getDouble("rating");

            Movie movie = new Movie.Builder()
                    .withTitle(title)
                    .withProductionYear(productionYear)
                    .withGenre(Genre.valueOf(genre))
                    .withRating(rating)
                    .create();
            System.out.println(movie);
        }
    }

    private Connection establishDBConnection(String password) throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, password);
    }

    private String getDBPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj hasło: ");
        return sc.nextLine();
    }
}
