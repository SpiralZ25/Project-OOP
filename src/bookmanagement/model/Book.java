package bookmanagement.model;

import bookmanagement.exception.BookUnavailableException;

public abstract class Book implements Borrowable {
    private String id;
    private String title;
    private String author;
    private String category;
    private boolean borrowed;

    public Book(String id, String title, String author, String category, boolean borrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.borrowed = borrowed;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public abstract String getType();

    @Override
    public void borrowBook() throws BookUnavailableException {
        if (borrowed == true) {
            throw new BookUnavailableException("This book is already borrowed.");
        }
        borrowed = true;
    }

    @Override
    public void returnBook() {
        borrowed = false;
    }

    @Override
    public String toString() {
        String status;

        if (borrowed == true) {
            status = "Borrowed";
        } else {
            status = "Available";
        }

        return "Type: " + getType()
                + " | ID: " + id
                + " | Title: " + title
                + " | Author: " + author
                + " | Category: " + category
                + " | Status: " + status;
    }
}