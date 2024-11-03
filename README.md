# Library Management System Using Data Structures (Java)

This project is a console-based Library Management System implemented in Java using linked lists. The application provides basic library management functionalities such as issuing books, returning issued books, and displaying records of issued books along with user details.

## Features

- **Issue Books**: Each book in the library has a unique identification number (Book ID). A user can issue a book by entering the Book ID along with user details.
- **Return Books**: When a user returns an issued book, the book becomes available for other users to issue.
- **Display Issued Books**: A record of all issued books, along with the corresponding user details, can be viewed.

**Note**: Each user is allowed to issue only one book at a time.

## Data Structure

This Library Management System uses a **linked list** data structure to manage records of issued books. Each node in the linked list represents an issued book and stores:
- **Book ID**: A unique identifier for each book.
- **User Details**: Details of the user who has issued the book (such as user name and ID).
- **Issue Date**: Date when the book was issued.

This structure makes it easy to dynamically add, remove, and view issued book records.

## Requirements

- **Java Development Kit (JDK)** version 8 or higher.
- A code editor such as IntelliJ IDEA, Eclipse, or Visual Studio Code for Java.

## Installation and Usage

1. **Clone the repository** or download the project files.
2. **Compile the project**: Open a terminal or command prompt, navigate to the project directory, and compile the Java files.
   ```bash
   javac LibraryManagementSystem.java
   ```
3. **Run the application**:
   ```bash
   java LibraryManagementSystem
   ```

## Program Structure

The Java implementation contains the following classes:

- **Book**: This class represents a book in the library with fields like `bookID`, `title`, and a flag indicating if the book is currently issued.
- **User**: Represents a user with fields like `userID`, `name`, and the `bookID` of the book currently issued (if any).
- **LibraryManagementSystem**: This main class contains methods to issue books, return books, and display records of issued books. It also includes a linked list to keep track of issued books.

## Main Operations

1. **Issue a Book**: Prompts the user to enter a Book ID and user details. If the book is available, it is issued to the user.
2. **Return a Book**: Takes the Book ID from the user and updates the book’s status as available.
3. **View Issued Book Records**: Displays a list of all currently issued books along with the details of the users who have them.

## Future Enhancements

Potential enhancements include:
- Adding functionality to handle multiple books issued per user.
- Adding a search feature for books and users.
- Implementing a graphical user interface (GUI).

## License

This project is open-source and free to use.
