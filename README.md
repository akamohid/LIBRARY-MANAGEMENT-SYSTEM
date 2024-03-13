# LIBRARY-MANAGEMENT-SYSTEM
Implementing via java and sqlite.

# Library Management System

This Java application is a simple Library Management System that allows librarians to manage books and user accounts. It enables librarians to perform tasks such as adding new books, adding new users, checking out books to users, returning books, and searching for books by title or author.

## Features

- **Book Class:** Represents a book with attributes such as book ID, title, author, genre, and availability status.
- **User Class:** Represents a library user with attributes such as user ID, name, contact information, and borrowed books.
- **Library Class:** Manages the collection of books and users. Implements methods for adding new books, adding new users, checking out books, returning books, and searching for books.
- **Main Class:** Entry point for the program. Provides a menu-driven interface for librarians to perform various tasks.

## Getting Started

To use the Library Management System, you need to have Java installed on your machine. Follow these steps:

1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-username/library-management-system.git

Navigate to the project directory:
cd library-management-system

Compile the Java files:
javac LibraryManagementSystem.java


Run the program:    
java LibraryManagementSystem

Usage
Follow the menu-driven interface to perform various tasks such as adding books, adding users, borrowing books, returning books, and searching for books. Input valid information as prompted by the program.

Error Handling
Invalid user inputs and edge cases are gracefully handled throughout the program. InputMismatchException and NumberFormatException are caught and appropriate error messages are displayed to the user.

License
This project is licensed under the MIT License.

This README.md file now provides relevant information about the Library Management System, including its features, how to get started, usage instructions, error handling, and license information.
