package bookmanagement;

import java.util.List;
import java.util.Scanner;

import bookmanagement.exception.BookNotFoundException;
import bookmanagement.exception.BookUnavailableException;
import bookmanagement.exception.DuplicateBookException;
import bookmanagement.manager.BookManager;
import bookmanagement.model.Book;
import bookmanagement.model.Novel;
import bookmanagement.model.TextBook;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookManager manager = new BookManager();

        int choice = -1;

        while (choice != 0) {
            System.out.println("========== Book Management System ==========");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Update Book");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Available Books");
            System.out.println("0. Exit");
            System.out.print("Choose menu: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    addBook(sc, manager);
                    break;
                case 2:
                    viewAllBooks(manager);
                    break;
                case 3:
                    deleteBook(sc, manager);
                    break;
                case 4:
                    updateBook(sc, manager);
                    break;
                case 5:
                    borrowBook(sc, manager);
                    break;
                case 6:
                    returnBook(sc, manager);
                    break;
                case 7:
                    viewAvailableBooks(manager);
                    break;
                case 0:
                    System.out.println("Program ended.");
                    break;
                default:
                    System.out.println("Invalid menu.");
            }

            System.out.println();
        }

        sc.close();
    }

    public static void addBook(Scanner sc, BookManager manager) {
        try {
            System.out.print("Enter type (1 = Novel, 2 = TextBook): ");
            int type = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter book ID: ");
            String id = sc.nextLine();

            System.out.print("Enter title: ");
            String title = sc.nextLine();

            System.out.print("Enter author: ");
            String author = sc.nextLine();

            System.out.print("Enter category: ");
            String category = sc.nextLine();

            Book book = null;

            if (type == 1) {
                book = new Novel(id, title, author, category, false);
            } else if (type == 2) {
                book = new TextBook(id, title, author, category, false);
            } else {
                System.out.println("Invalid type.");
                return;
            }

            manager.addBook(book);
            System.out.println("Book added successfully.");

        } catch (DuplicateBookException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void viewAllBooks(BookManager manager) {
        List<Book> books = manager.getAllBooks();

        if (books.size() == 0) {
            System.out.println("No books in the system.");
            return;
        }

        System.out.println("========== Book List ==========");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public static void deleteBook(Scanner sc, BookManager manager) {
        System.out.print("Enter book ID to delete: ");
        String id = sc.nextLine();

        try {
            manager.deleteBook(id);
            System.out.println("Book deleted successfully.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateBook(Scanner sc, BookManager manager) {
        System.out.print("Enter book ID to update: ");
        String id = sc.nextLine();

        try {
            System.out.print("Enter new title: ");
            String newTitle = sc.nextLine();

            System.out.print("Enter new author: ");
            String newAuthor = sc.nextLine();

            System.out.print("Enter new category: ");
            String newCategory = sc.nextLine();

            manager.updateBook(id, newTitle, newAuthor, newCategory);
            System.out.println("Book updated successfully.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void borrowBook(Scanner sc, BookManager manager) {
        System.out.print("Enter book ID to borrow: ");
        String id = sc.nextLine();

        try {
            manager.borrowBook(id);
            System.out.println("Book borrowed successfully.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (BookUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void returnBook(Scanner sc, BookManager manager) {
        System.out.print("Enter book ID to return: ");
        String id = sc.nextLine();

        try {
            manager.returnBook(id);
            System.out.println("Book returned successfully.");
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void viewAvailableBooks(BookManager manager) {
        List<Book> books = manager.getAvailableBooks();

        if (books.size() == 0) {
            System.out.println("No available books.");
            return;
        }

        System.out.println("========== Available Books ==========");
        for (Book b : books) {
            System.out.println(b);
        }
    }
}