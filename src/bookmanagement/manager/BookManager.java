package bookmanagement.manager;

import java.util.List;
import java.util.ArrayList;

import bookmanagement.exception.BookNotFoundException;
import bookmanagement.exception.BookUnavailableException;
import bookmanagement.exception.DuplicateBookException;
import bookmanagement.model.Book;
import bookmanagement.storage.Repository;

public class BookManager {
    private Repository<Book> repository;

    public BookManager() {
        repository = new Repository<Book>();
    }

    public void addBook(Book book) throws DuplicateBookException {
        List<Book> books = repository.getAll();

        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(book.getId())) {
                throw new DuplicateBookException("Book ID already exists.");
            }
        }

        repository.add(book);
    }

    public List<Book> getAllBooks() {
        return repository.getAll();
    }

    public Book searchBookById(String id) throws BookNotFoundException {
        List<Book> books = repository.getAll();

        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) {
                return b;
            }
        }

        throw new BookNotFoundException("Book not found.");
    }

    public void deleteBook(String id) throws BookNotFoundException {
        List<Book> books = repository.getAll();
        Book bookToRemove = null;

        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) {
                bookToRemove = b;
                break;
            }
        }

        if (bookToRemove == null) {
            throw new BookNotFoundException("Book not found.");
        }

        books.remove(bookToRemove);
    }

    public void updateBook(String id, String newTitle, String newAuthor, String newCategory)
            throws BookNotFoundException {
        Book b = searchBookById(id);

        b.setTitle(newTitle);
        b.setAuthor(newAuthor);
        b.setCategory(newCategory);
    }

    public void borrowBook(String id) throws BookNotFoundException, BookUnavailableException {
        Book b = searchBookById(id);
        b.borrowBook();
    }

    public void returnBook(String id) throws BookNotFoundException {
        Book b = searchBookById(id);
        b.returnBook();
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<Book>();
        List<Book> books = repository.getAll();

        for (Book b : books) {
            if (b.isBorrowed() == false) {
                availableBooks.add(b);
            }
        }

        return availableBooks;
    }
}