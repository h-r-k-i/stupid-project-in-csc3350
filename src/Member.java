import java.util.ArrayList;
import java.util.List;

public class Member {
    private String id;
    private String name;
    private String contactInfo;
    private List<Book> borrowedBooks;

    public Member(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return String.format("ID: %-10s Name: %-20s Contact: %-20s Borrowed Books: %d",
                id, name, contactInfo, borrowedBooks.size());
    }
}
