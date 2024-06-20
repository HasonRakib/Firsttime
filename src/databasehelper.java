import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class databasehelper {
    private static final String URL = "jdbc:sqlite:./SQLitedatabaselib/books.db"; // Path to your SQLite database

    /*static {
        try {
            // Load the SQLite JDBC driver (you must have the SQLite JAR file in your classpath)
            Class.forName("sqlite-jdbc-3.46.0.0.jar");
            System.out.println("SQLite JDBC driver registered.");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found. Make sure the SQLite JDBC library is included in the classpath.");
            e.printStackTrace();
        }
    }*/

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createTables() {
        String createBooksTable = "CREATE TABLE IF NOT EXISTS Books ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT NOT NULL,"
                + "author TEXT NOT NULL"
                + ");";

                try (Connection conn = connect();
                Statement stmt = conn != null ? conn.createStatement() : null) {
               if (stmt != null) {
                   stmt.execute(createBooksTable);
                   System.out.println("Tables created successfully.");
               } else {
                   System.out.println("Statement creation failed. Connection is null.");
               }
                 } catch (SQLException e) {
               System.out.println("SQL error: " + e.getMessage());
                }
    }

    public static void addBook(String title, String author) {
        String sql = "INSERT INTO Books(title, author) VALUES(?, ?)";

        try (Connection conn = connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public static void viewBooks() {
        String sql = "SELECT id, title, author FROM Books";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " +
                        rs.getString("title") + " by " +
                        rs.getString("author"));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public static void updateBook(int id, String newTitle, String newAuthor) {
        String sql = "UPDATE Books SET title = ?, author = ? WHERE id = ?";

        try (Connection conn = connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newTitle);
            pstmt.setString(2, newAuthor);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Book updated successfully.");
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public static void deleteBook(int id) {
        String sql = "DELETE FROM Books WHERE id = ?";

        try (Connection conn = connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Book deleted successfully.");
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}

