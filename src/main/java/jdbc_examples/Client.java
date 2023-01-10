package jdbc_examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    }

    private static Connection establishDBConnection(String password) throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, password);
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
