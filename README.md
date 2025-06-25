# 📚 Library Management System – Java + SQLite

Welcome to the **Library Management System**, a console-based Java application that manages books and users in a library using an SQLite database. It allows basic operations such as adding books, adding users, borrowing, and returning books — with real-time updates stored persistently in a database.

---

## 🛠 Features

- 📘 Add new books with title, author, genre, and availability
- 👤 Register users with name and contact info
- 🔄 Borrow and return books with validation
- 🔍 Search books by title or author
- 💾 Persistent storage using SQLite
- 🧪 Organized, maintainable Java code with modular structure

---

## 📂 Repository Structure

```
LIBRARY-MANAGEMENT-SYSTEM/
├── src/                         # Source code
│   ├── Book.java
│   ├── Library.java
│   ├── LibraryManagementSystem.java
│   └── User.java
├── target/classes/             # Compiled .class files (generated)
├── library.db                  # SQLite database file (generated)
├── pom.xml                     # Maven config (optional)
├── LICENSE                     # MIT License
├── .gitignore                  # Files/folders to ignore
├── CITATION.cff                # Citation metadata
└── README.md                   # You're here!
```

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 8 or higher
- SQLite JDBC Driver (bundled in code or add as dependency)
- (Optional) Maven for project management

### 📦 Compile

```bash
javac -d target/classes src/*.java
```

### ▶️ Run the System

```bash
java -cp target/classes LIBRARY-MANAGEMENT-SYSTEM.LibraryManagementSystem
```

Follow the menu in the terminal to:

- Add books
- Add users
- Borrow or return books

---

## 🧩 How It Works

- All data is stored in `library.db` using SQLite.
- The app ensures the required tables are created at runtime.
- It offers book and user lookup, availability checks, and borrowing logic.
- Modular OOP design: Book, User, Library, and the main system separated.

---

## 📜 License

This project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute.

---

## 🧠 Why It Matters

This project is ideal for beginners learning:

- Java + JDBC
- Database integration in Java
- CLI-based application design
- Object-oriented principles and file structure

---

## 📌 Project Info

🔗 GitHub Repository: [github.com/akamohid/LIBRARY-MANAGEMENT-SYSTEM](https://github.com/akamohid/LIBRARY-MANAGEMENT-SYSTEM)  
👤 Author: [Mohid Arshad](https://linkedin.com/in/mohid-arshad-347180235/)
📧 Email: [Mohid Arshad](mailto:akamohid@gmail.com)

Enjoy managing your library! 📖✨