import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public boolean removeBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("No book found with ISBN: " + isbn);
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Cannot remove book, it is currently borrowed: " + book.getTitle());
            return false;
        }
        books.remove(book);
        System.out.println("Book removed: " + book.getTitle());
        return true;
    }

    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    public boolean deregisterMember(String id) {
        Member member = findMemberById(id);
        if (member == null) {
            System.out.println("No member found with ID: " + id);
            return false;
        }
        if (!member.getBorrowedBooks().isEmpty()) {
            System.out.println("Cannot de-register member, outstanding borrowed books: " + member.getName());
            return false;
        }
        members.remove(member);
        System.out.println("Member de-registered: " + member.getName());
        return true;
    }

    public boolean borrowBook(String memberId, String isbn) {
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("No member found with ID: " + memberId);
            return false;
        }
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("No book found with ISBN: " + isbn);
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed: " + book.getTitle());
            return false;
        }
        book.setAvailable(false);
        member.borrowBook(book);
        System.out.println(member.getName() + " borrowed \"" + book.getTitle() + "\"");
        return true;
    }

    public boolean returnBook(String memberId, String isbn) {
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("No member found with ID: " + memberId);
            return false;
        }
        Book book = findBookByIsbn(isbn);
        if (book == null || !member.getBorrowedBooks().contains(book)) {
            System.out.println("This member did not borrow a book with ISBN: " + isbn);
            return false;
        }
        book.setAvailable(true);
        member.returnBook(book);
        System.out.println(member.getName() + " returned \"" + book.getTitle() + "\"");
        return true;
    }

    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    public Member findMemberById(String id) {
        for (Member member : members) {
            if (member.getId().equalsIgnoreCase(id)) {
                return member;
            }
        }
        return null;
    }

    public List<Book> searchByTitle(String title) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> searchByIsbn(String isbn) {
        List<Book> results = new ArrayList<>();
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            results.add(book);
        }
        return results;
    }

    public void displayAvailableBooks() {
        System.out.println("--- Available Books ---");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books.");
        }
    }

    public void displayBorrowedBooks() {
        System.out.println("--- Borrowed Books ---");
        boolean found = false;
        for (Member member : members) {
            for (Book book : member.getBorrowedBooks()) {
                System.out.println(book + "  |  Borrowed by: " + member.getName() + " (ID: " + member.getId() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books are currently borrowed.");
        }
    }

    public void displayAllBooks() {
        System.out.println("--- All Books ---");
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void displayAllMembers() {
        System.out.println("--- All Members ---");
        if (members.isEmpty()) {
            System.out.println("No registered members.");
            return;
        }
        for (Member member : members) {
            System.out.println(member);
        }
    }
}
