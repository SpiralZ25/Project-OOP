package bookmanagement.model;

import bookmanagement.exception.BookUnavailableException;

public interface Borrowable {
    void borrowBook() throws BookUnavailableException;
    void returnBook();
}