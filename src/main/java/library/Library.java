package library;

import java.sql.*;

public class Library {
    public Connection connection;

    public Library(Connection connection) {
        this.connection = connection;
        createTablesIfNotExists();
    }

    public void createTablesIfNotExists() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY, title TEXT, author TEXT, genre TEXT, availability INTEGER)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT, contact_info TEXT)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS borrowed_books (user_id INTEGER, book_id INTEGER)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO books (id, title, author, genre, availability) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, book.getBookID());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getGenre());
            stmt.setInt(5, book.isAvailability() ? 1 : 0);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void addUser(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (id, name, contact_info) VALUES (?, ?, ?)")) {
            stmt.setInt(1, user.getUserID());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getContactInfo());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public Book findBookByTitle(String title) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM books WHERE title = ?")) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("genre"));
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Book findBookByAuthor(String author) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM books WHERE author = ?")) {
            stmt.setString(1, author);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("genre"));
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public User findUserByID(int userID) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return new User(rs.getInt("id"), rs.getString("name"), rs.getString("contact_info"));
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void checkoutBook(User user, Book book) {
        try {
            try (PreparedStatement stmt = connection.prepareStatement("UPDATE books SET availability = ? WHERE id = ?")) {
                stmt.setInt(1, 0);
                stmt.setInt(2, book.getBookID());
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO borrowed_books (user_id, book_id) VALUES (?, ?)")) {
                stmt.setInt(1, user.getUserID());
                stmt.setInt(2, book.getBookID());
                stmt.executeUpdate();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void returnBook(User user, Book book) {
        try {
            try (PreparedStatement stmt = connection.prepareStatement("UPDATE books SET availability = ? WHERE id = ?")) {
                stmt.setInt(1, 1);
                stmt.setInt(2, book.getBookID());
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM borrowed_books WHERE user_id = ? AND book_id = ?")) {
                stmt.setInt(1, user.getUserID());
                stmt.setInt(2, book.getBookID());
                stmt.executeUpdate();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
