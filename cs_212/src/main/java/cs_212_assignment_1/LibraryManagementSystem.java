package cs_212_assignment_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
 
public class LibraryManagementSystem {
    public static Connection connection;

    public static void main(String[] args) {
         try {
            connection = DriverManager.getConnection("jdbc:sqlite:library.db");
            Library library = new Library(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Add Book");
                System.out.println("2. Add User");
                System.out.println("3. Borrow Book");
                System.out.println("4. Return Book");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
 
                switch (choice) {
                    case 1:
                        addBook(library, scanner);
                        break;
                    case 2:
                        addUser(library, scanner);
                        break;
                    case 3:
                        borrowBook(library, scanner);
                        break;
                    case 4:
                        returnBook(library, scanner);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        connection.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }  
        catch (SQLException | ClassCastException e) {
            e.printStackTrace();
        }
    }
 
    public static void addBook(Library library, Scanner scanner) {
        System.out.print("Enter book ID: ");
        int bookID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        Book book = new Book(bookID, title, author, genre);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }
 
    public static void addUser(Library library, Scanner scanner) {
        System.out.print("Enter user ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact info: ");
        String contactInfo = scanner.nextLine();
        User user = new User(userID, name, contactInfo);
        library.addUser(user);
        System.out.println("User added successfully.");
    }
 
    public static void borrowBook(Library library, Scanner scanner) {
        while (true) {
            System.out.print("Enter user ID: ");
            int userID = scanner.nextInt();
            scanner.nextLine();
            User user = library.findUserByID(userID);
            if (user == null) {
                System.out.println("User not found. Please try again.");
                continue;
            }
    
            
            System.out.println("1. Find book by title");
            System.out.println("2. Find book by author");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
    
            switch (choice) {
                case 1: {
                    System.out.print("Enter book's title: ");
                    String title = scanner.nextLine();
                    Book book = library.findBookByTitle(title);
                    if (book == null) {
                        System.out.println("Book not found.");
                        return;
                    }
                    library.checkoutBook(user, book);
                    System.out.println("Book '" + book.getTitle() + "' checked out to user '" + user.getName() + "'.");
                    return;
                }
                case 2: {
                    System.out.print("Enter book's author: ");
                    String author = scanner.nextLine();
                    Book book = library.findBookByAuthor(author);
                    if (book == null) {
                        System.out.println("Book not found.");
                        return;
                    }
                    library.checkoutBook(user, book);
                    System.out.println(book.getAuthor() + "''s book checked out to user '" + user.getName() + "'.");
                    return;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
            }break;
        }
    }
 
    public static void returnBook(Library library, Scanner scanner) {
        System.out.print("Enter user ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        User user = library.findUserByID(userID);
 
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        Book book = library.findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        library.returnBook(user, book);
    }
}