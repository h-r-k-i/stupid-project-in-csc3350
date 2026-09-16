public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private boolean available;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.available = true;
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

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format("ISBN: %-15s Title: %-30s Author: %-20s Year: %-6d Status: %s",
                isbn, title, author, publicationYear, available ? "Available" : "Borrowed");
    }
}
