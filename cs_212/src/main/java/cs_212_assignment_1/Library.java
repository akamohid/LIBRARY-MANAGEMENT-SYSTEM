package cs_212_assignment_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Library {
    public Connection connection;

    public Library(Connection connection) {
        this.connection = connection;
        createTablesIfNotExists();
    }

    public void createTablesIfNotExists() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY, title TEXT, author TEXT, genre TEXT, availability INTEGER)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT, contact_info TEXT)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS borrowed_books (user_id INTEGER, book_id INTEGER)");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO books (id, title, author, genre, availability) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, book.getBookID());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getGenre());
            statement.setInt(5, book.isAvailability() ? 1 : 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (id, name, contact_info) VALUES (?, ?, ?)");
            statement.setInt(1, user.getUserID());
            statement.setString(2, user.getName());
            statement.setString(3, user.getContactInfo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book findBookByTitle(String title) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE title = ?");
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Book(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("author"), resultSet.getString("genre"));
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Book findBookByAuthor(String author) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE author = ?");
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Book(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("author"), resultSet.getString("genre"));
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findUserByID(int userID) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("contact_info"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void checkoutBook(User user, Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE books SET availability = ? WHERE id = ?");
            statement.setInt(1, 0);
            statement.setInt(2, book.getBookID());
            statement.executeUpdate();

            statement = connection.prepareStatement("INSERT INTO borrowed_books (user_id, book_id) VALUES (?, ?)");
            statement.setInt(1, user.getUserID());
            statement.setInt(2, book.getBookID());
            statement.executeUpdate();

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(User user, Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE books SET availability = ? WHERE id = ?");
            statement.setInt(1, 1);
            statement.setInt(2, book.getBookID());
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM borrowed_books WHERE user_id = ? AND book_id = ?");
            statement.setInt(1, user.getUserID());
            statement.setInt(2, book.getBookID());
            statement.executeUpdate();

            System.out.println("Book '" + book.getTitle() + "' returned by user '" + user.getName() + "'.");
        } 
        catch (SQLException | ClassCastException e) {
            e.printStackTrace();
        }
    }
}