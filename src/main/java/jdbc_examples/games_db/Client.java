package jdbc_examples.games_db;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/games";
    private static final String DATABASE_LOGIN = "root";

    private static final String ADD_GAME_QUERY_TEMPLATE =
            "INSERT INTO games(title, price, platform) VALUES (?,?,?);";
//    private static final String ADD_GAME_QUERY_TEMPLATE =
//            "INSERT INTO games(title, price, platform) VALUES ('%s',%d,'%s');";
    public static void main(String[] args) {
        String password = getDBPassword();

        try (Connection conn = establishDBConnection(password)) {
            createTableSql(conn);
            addSomeGames(conn);
            updateGame(conn);
            deleteGame(conn);
            ResultSet games = readGames(conn);
            displayResults(games);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayResults(ResultSet games) throws SQLException {
        while (games.next()) {
                int id = games.getInt("id");
                String title = games.getString("title");
                int price = games.getInt("price");
                String platform = games.getString("platform");
                System.out.println("id: " + id + "\ttitle: " + title + "\tprice: " + price + "\tplatform: " + platform);
            }
    }

    private static void createTableSql(Connection conn) throws SQLException {
        String createTableSql = """
                    CREATE TABLE games(
                    id int AUTO_INCREMENT NOT NULL,
                    title varchar(255) NOT NULL,
                    price int NOT NULL,
                    platform varchar(255) NOT NULL,
                    PRIMARY KEY (id)
                    );
                    """;
        conn.createStatement().execute(createTableSql);
    }

    private static void addSomeGames(Connection conn) throws SQLException {
        Game g1 = new Game("Slay the Spire", 20, "PC");
        Game g2 = new Game("Stardew Valley", 10, "PC");
        Game g3 = new Game("Breath of the Wild", 40, "Switch");
        Game g4 = new Game("Bayonetta 3", 35, "Switch");
        List<Game> gameList = List.of(g1, g2, g3, g4);
        for (Game game : gameList) {
            PreparedStatement ps = conn.prepareStatement(ADD_GAME_QUERY_TEMPLATE);
            ps.setString(1, game.getTitle());
            ps.setInt(2, game.getPrice());
            ps.setString(3, game.getPlatform());
            ps.execute();
        }
    }

    private static void updateGame(Connection conn) throws SQLException {
        String updateGameSql = """
                UPDATE games
                SET title='Legend of Zelda: Breath of the Wild'
                WHERE title='Breath of the Wild';
                """;
        conn.createStatement().execute(updateGameSql);
    }

    private static void deleteGame(Connection conn) throws SQLException {
        String deleteGameSql = "DELETE FROM games WHERE platform='PC';";
        conn.createStatement().execute(deleteGameSql);
    }

    private static ResultSet readGames(Connection conn) throws SQLException {
        String selectAllGamesSql = "SELECT * FROM games";
        return conn.createStatement().executeQuery(selectAllGamesSql);
    }

    private static String getDBPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj hasło: ");
        return sc.nextLine();
    }

    private static Connection establishDBConnection(String password) throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, password);
    }
}
