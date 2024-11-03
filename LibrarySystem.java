import java.util.Scanner;

class Book {
    String name;
    String author;
    int id;
    Book next;

    public Book(String name, String author, int id) {
        this.name = name;
        this.author = author;
        this.id = id;
        this.next = null;
    }
}

class Student {
    String name;
    String email;
    String bookName;
    String author;
    int id;
    Student next;

    public Student(String name, String email, String bookName, String author, int id) {
        this.name = name;
        this.email = email;
        this.bookName = bookName;
        this.author = author;
        this.id = id;
        this.next = null;
    }
}

public class LibrarySystem {
    private static Book startLib = null;
    private static Student start = null;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Test output");
        startLib = initializeLib();
        greetings();
        mainMenu();
    }

    private static void greetings() {
        System.out.println("\n\n\t\t\t     ****************************************");
        System.out.println("\t\t\t     *                                      *");
        System.out.println("\t\t\t     *     WELCOME TO STUDENT LIBRARY       *");
        System.out.println("\t\t\t     *       Mumbai, Maharashtra, India     *");
        System.out.println("\t\t\t     *     Email: studentlib@gmail.com      *");
        System.out.println("\t\t\t     *     Contact: 8800991010              *");
        System.out.println("\t\t\t     ****************************************");
        System.out.print("\n\n\t\t\t Press any key to continue: ");
        scanner.nextLine();
    }

    private static void mainMenu() {
        int choice;
        do {
            System.out.println("\n\t\t\t********************* MAIN MENU *********************");
            System.out.println("\t\t\t\t 1. ISSUE OF BOOKS ");
            System.out.println("\t\t\t\t 2. RETURN OF BOOKS ");
            System.out.println("\t\t\t\t 3. DISPLAY STUDENT DETAILS ");
            System.out.println("\t\t\t\t 4. EXIT ");
            System.out.print("\n\t\t\t\t Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> start = bookIssue(start);
                case 2 -> start = bookReturn(start);
                case 3 -> displayStudents(start);
                case 4 -> System.exit(0);
                default -> System.out.println("\n\t\t\t Invalid Option! Try again.");
            }
        } while (choice != 4);
    }

    private static Book initializeLib() {
        Book b1 = new Book("The Kite Runner", "Khaled Hosseini", 101);
        Book b2 = new Book("To Kill A Mockingbird", "Harper Lee", 102);
        Book b3 = new Book("The Alchemist", "Paulo Coelho", 103);
        Book b4 = new Book("Pride And Prejudice", "Jane Austen", 104);
        Book b5 = new Book("A Tale Of Two Cities", "Charles Dickens", 105);

        b1.next = b2;
        b2.next = b3;
        b3.next = b4;
        b4.next = b5;

        return b1;
    }

    private static Student bookIssue(Student start) {
        if (startLib == null) {
            System.out.println("\nNo books left in the library to issue!");
            return start;
        }

        Book ptr = startLib;
        System.out.println("\n*************** Books Available: ****************");
        while (ptr != null) {
            System.out.printf("\nBook Title: %s\nAuthor: %s\nBook ID: %d\n", ptr.name, ptr.author, ptr.id);
            ptr = ptr.next;
        }

        System.out.print("\nEnter the Book ID: ");
        int id = scanner.nextInt();
        Book selectedBook = findBookById(id);

        if (selectedBook != null) {
            scanner.nextLine();  // Clear buffer
            System.out.print("Enter Student Name: ");
            String studentName = scanner.nextLine();
            System.out.print("Enter Student Email: ");
            String email = scanner.nextLine();

            Student newStudent = new Student(studentName, email, selectedBook.name, selectedBook.author, selectedBook.id);
            if (start == null) {
                start = newStudent;
            } else {
                Student temp = start;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newStudent;
            }

            startLib = deleteBook(selectedBook.id);
            System.out.println("\nIssue of Book ID " + selectedBook.id + " done successfully!");
        } else {
            System.out.println("\nInvalid Book ID! Try again.");
        }

        return start;
    }

    private static Student bookReturn(Student start) {
        if (start == null) {
            System.out.println("\nNo student record found!");
            return start;
        }

        System.out.print("\nEnter your Book ID: ");
        int id = scanner.nextInt();
        Student studentToReturn = findStudentById(start, id);

        if (studentToReturn != null) {
            System.out.printf("\nStudent Name: %s\nStudent Email: %s\nBook Issued: %s\n", studentToReturn.name, studentToReturn.email, studentToReturn.bookName);

            start = removeStudent(start, id);
            addBook(studentToReturn.bookName, studentToReturn.author, studentToReturn.id);
            System.out.println("\nReturn of Book ID " + id + " done successfully!");
        } else {
            System.out.println("\nSorry, the book ID doesn't exist! Try again.");
        }

        return start;
    }

    private static void displayStudents(Student start) {
        Student ptr = start;
        if (ptr == null) {
            System.out.println("\nNo students have issued books.");
            return;
        }

        System.out.println("\n************* Details of Students: **************");
        while (ptr != null) {
            System.out.printf("\nStudent Name: %s\nEmail: %s\nBook Issued: %s\nBook ID: %d\n", ptr.name, ptr.email, ptr.bookName, ptr.id);
            ptr = ptr.next;
        }
    }

    private static Book deleteBook(int id) {
        if (startLib == null) return null;
        if (startLib.id == id) return startLib.next;

        Book prev = startLib;
        Book current = startLib.next;

        while (current != null && current.id != id) {
            prev = current;
            current = current.next;
        }

        if (current != null) {
            prev.next = current.next;
        }
        return startLib;
    }

    private static void addBook(String name, String author, int id) {
        Book newBook = new Book(name, author, id);

        if (startLib == null) {
            startLib = newBook;
        } else {
            Book ptr = startLib;
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            ptr.next = newBook;
        }
    }

    private static Book findBookById(int id) {
        Book ptr = startLib;
        while (ptr != null) {
            if (ptr.id == id) return ptr;
            ptr = ptr.next;
        }
        return null;
    }

    private static Student findStudentById(Student start, int id) {
        Student ptr = start;
        while (ptr != null) {
            if (ptr.id == id) return ptr;
            ptr = ptr.next;
        }
        return null;
    }

    private static Student removeStudent(Student start, int id) {
        if (start == null) return null;
        if (start.id == id) return start.next;

        Student prev = start;
        Student current = start.next;

        while (current != null && current.id != id) {
            prev = current;
            current = current.next;
        }

        if (current != null) {
            prev.next = current.next;
        }
        return start;
    }
}
