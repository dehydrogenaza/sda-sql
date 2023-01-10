package jdbc_examples.books_db;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Client {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/books";
    private static final String DATABASE_LOGIN = "root";
    private static final String INSERT_BOOK_QUERY_TEMPLATE = "INSERT INTO books(title, author, pages) VALUES ('%s','%s',%d);";

    public static void main(String[] args) {
        String password = getDBPassword();
        String createTableSql = """
                    CREATE TABLE books(
                    id int AUTO_INCREMENT NOT NULL,
                    title varchar(255) NOT NULL,
                    author varchar(255) NOT NULL,
                    pages int NOT NULL,
                    PRIMARY KEY (id)
                    );
                    """;

        Book book = new Book("Harry Potter", "J. K. Rowling", 300);
        Book book2 = new Book("Ubik", "Philip K. Dick", 250);

        //executeDBActions(password, createTableSql);
        //executeDBActions(password, book.toQuery(INSERT_BOOK_QUERY_TEMPLATE));
        //executeDBActions(password, book2.toQuery(INSERT_BOOK_QUERY_TEMPLATE));

        String updateBookSql = """
                UPDATE books b
                SET author='P. K. Dick'
                WHERE title='Ubik';
                """;

        //executeDBActions(password, updateBookSql);

        String selectAllBooksSql = "SELECT * FROM books";
        String selectSomeBooksSql = "SELECT * FROM books WHERE pages<275";
//        try {
//            Connection conn = establishDBConnection(password);
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery(selectAllBooksSql);
//            //trzeba przesunąć KURSOR
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String title = resultSet.getString("title");
//                System.out.println("id: " + id + "\ttitle: " + title);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        List<ResultSet> resultSets2 = executeDBQueries(password, selectSomeBooksSql);

        String deleteSql = "DELETE FROM books WHERE id=2;";
        //executeDBActions(password, deleteSql);

        //SQL Injection
        Book attackBook = new Book("'a','a',1); drop database books; --", "", 250);
        //lepiej zawsze używać PREPARED STATEMENT zamiast STATEMENT, które chroni przed SQL Injection
        try {
            Connection conn = establishDBConnection(password);
            String insertSql = "INSERT INTO books VALUES(0,?,?,?);";
            PreparedStatement statement = conn.prepareStatement(insertSql);
            statement.setString(1, "Czysta Architektura"); //indeks od 1!
            statement.setString(2, "Robert Martin");
            statement.setInt(3, 400);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection establishDBConnection(String password) throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, password);
    }

    private static List<ResultSet> executeDBQueries(String password, String... queries) {
        try (Connection conn = establishDBConnection(password)) {
            Statement statement = conn.createStatement();
            List<ResultSet> resultSets = new LinkedList<>();
            for (String query : queries) {
                resultSets.add(statement.executeQuery(query));
            }
            return resultSets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeDBActions(String password, String... queries) {
        try (Connection conn = establishDBConnection(password)) {
            Statement statement = conn.createStatement();
            for (String query : queries) {
                statement.execute(query);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getDBPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj hasło: ");
        return sc.nextLine();
    }
}
