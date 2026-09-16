import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Library library = new Library();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedSampleData();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    removeBook();
                    break;
                case "3":
                    registerMember();
                    break;
                case "4":
                    deregisterMember();
                    break;
                case "5":
                    borrowBook();
                    break;
                case "6":
                    returnBook();
                    break;
                case "7":
                    searchBooks();
                    break;
                case "8":
                    library.displayAvailableBooks();
                    break;
                case "9":
                    library.displayBorrowedBooks();
                    break;
                case "10":
                    library.displayAllBooks();
                    break;
                case "11":
                    library.displayAllMembers();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("===== School Library Management System =====");
        System.out.println("1.  Add Book");
        System.out.println("2.  Remove Book");
        System.out.println("3.  Register Member");
        System.out.println("4.  De-register Member");
        System.out.println("5.  Borrow Book");
        System.out.println("6.  Return Book");
        System.out.println("7.  Search Books");
        System.out.println("8.  Display Available Books");
        System.out.println("9.  Display Borrowed Books");
        System.out.println("10. Display All Books");
        System.out.println("11. Display All Members");
        System.out.println("0.  Exit");
        System.out.print("Choose an option: ");
    }

    private static void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();
        System.out.print("Publication Year: ");
        int year = readInt();
        library.addBook(new Book(title, author, isbn, year));
    }

    private static void removeBook() {
        System.out.print("Enter ISBN of book to remove: ");
        String isbn = scanner.nextLine().trim();
        library.removeBook(isbn);
    }

    private static void registerMember() {
        System.out.print("Member ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Contact Info: ");
        String contact = scanner.nextLine().trim();
        library.registerMember(new Member(id, name, contact));
    }

    private static void deregisterMember() {
        System.out.print("Enter Member ID to de-register: ");
        String id = scanner.nextLine().trim();
        library.deregisterMember(id);
    }

    private static void borrowBook() {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();
        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine().trim();
        library.borrowBook(memberId, isbn);
    }

    private static void returnBook() {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();
        System.out.print("Book ISBN: ");
        String isbn = scanner.nextLine().trim();
        library.returnBook(memberId, isbn);
    }

    private static void searchBooks() {
        System.out.println("Search by: 1) Title  2) Author  3) ISBN");
        String option = scanner.nextLine().trim();
        System.out.print("Enter search term: ");
        String term = scanner.nextLine().trim();

        List<Book> results;
        switch (option) {
            case "1":
                results = library.searchByTitle(term);
                break;
            case "2":
                results = library.searchByAuthor(term);
                break;
            case "3":
                results = library.searchByIsbn(term);
                break;
            default:
                System.out.println("Invalid search option.");
                return;
        }

        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("--- Search Results ---");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    private static int readInt() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private static void seedSampleData() {
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "978-0345339683", 1937));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "978-0061120084", 1960));
        library.addBook(new Book("1984", "George Orwell", "978-0451524935", 1949));

        library.registerMember(new Member("M001", "Alice Johnson", "alice@example.com"));
        library.registerMember(new Member("M002", "Bob Smith", "bob@example.com"));
        System.out.println();
    }
}
