package LMS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Member> members = new ArrayList<>();
    private static final List<Book> books = new ArrayList<>();
    private static final List<Librarian> librarians = new ArrayList<>();
    private static Member loggedInMember = null;
    private static Librarian loggedInLibrarian = null;

    public static void main(String[] args) {
        initializeBooks();
        while (true) {
        	 System.out.println("\nLibrary Management System");
             System.out.println("1. Register Member");
             System.out.println("2. Register Librarian");
             System.out.println("3. Member Login");
             System.out.println("4. Librarian Login");
             System.out.println("5. Exit");
             System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
            case 1 -> registerMember();
            case 2 -> registerLibrarian();
            case 3 -> memberLogin();
            case 4 -> librarianLogin();
            case 5 -> {
                System.out.println("Exiting...");
                return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void registerMember() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();

        for (Member member : members) {
            if (member.getUsername().equals(username)) {
                System.out.println("Username already exists! Try again.");
                return;
            }
        }

        members.add(new Member(username, password, name, contactInfo));
        System.out.println("Registration successful!");
    }
    private static void registerLibrarian() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(username)) {
                System.out.println("Username already exists! Try again.");
                return;
            }
        }

        librarians.add(new Librarian(username, password, name));
        System.out.println("Librarian registration successful!");
    }
    private static void memberLogin() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (Member member : members) {
            if (member.getUsername().equals(username) && member.getPassword().equals(password)) {
                loggedInMember = member;
                System.out.println("Login successful! Welcome, " + loggedInMember.getUsername() + "!");
                memberMenu();
                return;
            }
        }

        System.out.println("Invalid credentials!");
    }
    private static void librarianLogin() {
        System.out.print("Enter Librarian Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Librarian Password: ");
        String password = scanner.nextLine();

        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(username) && librarian.getPassword().equals(password)) {
                loggedInLibrarian = librarian;
                System.out.println("Librarian login successful! Welcome, " + loggedInLibrarian.getName() + "!");
                librarianMenu();
                return;
            }
        }

        System.out.println("Invalid librarian credentials!");
    }
    private static void memberMenu() {
        while (true) {
            System.out.println("\nWelcome, " + loggedInMember.getUsername());
            System.out.println("1. View Profile");
            System.out.println("2. Search Books");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1 -> System.out.println(loggedInMember);
                case 2 -> searchBooks();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> payFine();
                case 6 -> {
                    System.out.println("Logged out successfully!");
                    loggedInMember = null;
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void searchBooks() {
        System.out.print("Enter keyword (Title, Author, or ISBN): ");
        String keyword = scanner.nextLine();
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword) || book.getIsbn().equals(keyword)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found for the given keyword.");
        }
    }

    private static void borrowBook() {
        System.out.print("Enter Book ID to borrow: ");
        String bookID = scanner.nextLine();
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                if (book.getCopiesAvailable() > 0) {
                    book.borrowBook();
                    loggedInMember.borrowBook(book);
                    System.out.println("Book borrowed successfully!");
                    return;
                } else {
                    System.out.println("No copies available.");
                    return;
                }
            }
        }
        System.out.println("Invalid Book ID.");
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        String bookID = scanner.nextLine();
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                if (loggedInMember.returnBook(book)) {
                    book.returnBook();
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("This book was not borrowed by you.");
                }
                return;
            }
        }
        System.out.println("Invalid Book ID.");
    }

    private static void payFine() {
        System.out.println("Your total fine is: $" + loggedInMember.getFine());
        System.out.print("Enter amount to pay: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear newline

        if (amount <= loggedInMember.getFine()) {
            loggedInMember.payFine(amount);
            System.out.println("Fine paid successfully! Remaining fine: $" + loggedInMember.getFine());
        } else {
            System.out.println("You entered more than your fine amount. Please retry.");
        }
    }

    private static void librarianMenu() {
        while (true) {
            System.out.println("\nLibrarian Menu");
            System.out.println("1. Add a Book");
            System.out.println("2. Remove a Book");
            System.out.println("3. View All Books");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> viewBooks();
                case 4 -> {
                    System.out.println("Librarian logged out.");
                    setLoggedInLibrarian(null);
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public static Librarian getLoggedInLibrarian() {
		return loggedInLibrarian;
	}

	public static void setLoggedInLibrarian(Librarian loggedInLibrarian) {
		LibraryManagementSystem.loggedInLibrarian = loggedInLibrarian;
	}

	private static void addBook() {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Number of Copies: ");
        int copies = scanner.nextInt();
        scanner.nextLine(); // Clear newline

        books.add(new Book(title, author, isbn, copies));
        System.out.println("Book added successfully!");
    }

    private static void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        String bookID = scanner.nextLine();
        books.removeIf(book -> book.getBookID().equals(bookID));
        System.out.println("Book removed successfully!");
    }

    private static void viewBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void initializeBooks() {
        books.add(new Book("Java Programming", "John Smith", "1234567890", 5));
        books.add(new Book("Python Essentials", "Jane Doe", "9876543210", 3));
        books.add(new Book("Data Structures", "Robert Martin", "1928374650", 7));
        books.add(new Book("Algorithms Unlocked", "Thomas Cormen", "1112131415", 2));
        books.add(new Book("Data and File Structure", "DR>Arvind Kalia", "11121314345", 4));
        books.add(new Book("o Kill a Mockingbird", " Harper Lee", "1115631415", 11));
        books.add(new Book("The Alchemist", " Paulo Coelho", "11151498875415", 12));
        books.add(new Book("Pride and Prejudice", "  Jane Austen", "111514234875415", 13));
        books.add(new Book("The Hunger Games", "  Suzanne Collin", "158634875415", 14));
        books.add(new Book("The Da Vinci Code", "  Dan Brown", "158675415", 15));
        books.add(new Book("The Catcher in the Rye" , "J.D Savirkar", "15895415", 16));
        books.add(new Book("The Adventures of Sherlock Holmes", "  Sir Arthur Conan Doyle", "155415", 17));
        books.add(new Book("The Picture of Dorian Gray", "Oscar Wilde", "158676415", 18));
        books.add(new Book("The Handmaid's Tale", "  Margaret Atwood", "158670015", 19));
        books.add(new Book("1984", "  George Orwell", "15815", 20));
        books.add(new Book("Making India Awesome", "  Chetan Bhagat    ", "158677815", 21));
        books.add(new Book("A Million Mutinies Now ", " V.S. Naipaul", "15905415", 22));
        books.add(new Book("A Brush with Life ", "  Satish Gujral ", "158555415", 23));
        System.out.println("Books initialized successfully.");
        
    }
}

class Member {
    private static int idCounter = 1000;
    private final String memberID;
    private String username;
    private String password;
    private String name;
    private String contactInfo;
    private final List<Book> borrowedBooks = new ArrayList<>();
    private double fine = 0;

    public Member(String username, String password, String name, String contactInfo) {
        this.memberID = "MEM" + (++idCounter);
        this.username = username;
        this.password = password;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getFine() {
        return fine;
    }

    public void payFine(double amount) {
        fine -= amount;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        fine += 5; // Simulating fine calculation for borrowed books.
    }

    public boolean returnBook(Book book) {
        return borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "MemberID: " + memberID + ", Name: " + name + ", Contact Info: " + contactInfo + ", Fine: $" + fine;
    }
}
class Librarian {
    private static int idCounter = 500;
    private final String librarianID;
    private final String username;
    private final String password;
    private final String name;

    public Librarian(String username, String password, String name) {
        this.librarianID = "LIB" + (++idCounter);
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "LibrarianID: " + librarianID + ", Name: " + name;
    }
}
class Book {
    private static int idCounter = 1000;
    private final String bookID;
    private final String title;
    private final String author;
    private final String isbn;
    private int copiesAvailable;

    public Book(String title, String author, String isbn, int copiesAvailable) {
        this.bookID = "BOOK" + (++idCounter);
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.copiesAvailable = copiesAvailable;
    }

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void borrowBook() {
        copiesAvailable--;
    }

    public void returnBook() {
        copiesAvailable++;
    }

    @Override
    public String toString() {
        return "BookID: " + bookID + ", Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Copies Available: " + copiesAvailable;
    }
}