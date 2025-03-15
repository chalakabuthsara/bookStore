package service;

import exceptions.AuthorNotFoundException;
import exceptions.BookNotFoundException;
import exceptions.InvalidInputException;
import model.Book;

import java.time.Year;
import java.util.ArrayList;

public class BookService {
    private static BookService instance;
    private ArrayList<Book> books;

    private BookService() {
        books = new ArrayList<>();
    }

    public static BookService getInstance(){
        if(instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public void addBook(Book book) throws InvalidInputException, AuthorNotFoundException {
        for (Book existingBook : books) {
            if (existingBook.getBookId().equals(book.getBookId())) {
                throw new InvalidInputException("Book ID already exists.");
            }
        }
        for (Book existingBook : books) {
            if (existingBook.getIsbn().equals(book.getIsbn())) {
                throw new InvalidInputException("ISBN is already in use.");
            }
        }
        validateBook(book);

        books.add(book);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Book getBook(Long id) throws BookNotFoundException {
        for(Book book: books) {
            if(book.getBookId().equals(id)) {
                return book;
            }
        }
        throw new BookNotFoundException("Book with id: " + id + " not found");
    }

    public Book updateBook(Long id, Book updatedBook) throws BookNotFoundException, InvalidInputException, AuthorNotFoundException {
        validateBook(updatedBook);

        for(Book book: books) {
            if(book.getBookId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthorId(updatedBook.getAuthorId());
                book.setIsbn(updatedBook.getIsbn());
                book.setPrice(updatedBook.getPrice());
                book.setPublicationYear(updatedBook.getPublicationYear());
                book.setStockQuantity(updatedBook.getStockQuantity());
                return book;
            }
        }
        throw new BookNotFoundException("Book with id: " + id + " not found");
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId().equals(id)) {
                books.remove(i);
                return;
            }
        }
        throw new BookNotFoundException("Book with id: " + id + " not found");
    }

    public void validateBook(Book book) throws InvalidInputException, AuthorNotFoundException {
        if (book == null) {
            throw new InvalidInputException("Book information is missing.");
        }

        if (book.getBookId() == null || book.getBookId().toString().trim().isEmpty()) {
            throw new InvalidInputException("bookId shouldn't be empty.");
        }

        if (book.getAuthorId() == null || book.getAuthorId().toString().trim().isEmpty()) {
            throw new InvalidInputException("authorId shouldn't be empty.");
        }

        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("title shouldn't be empty.");
        }

        if (book.getPrice() <= 0) {
            throw new InvalidInputException("price shouldn't be zero or negative.");
        }
        int currentYear = Year.now().getValue();
        if (book.getPublicationYear() > currentYear) {
            throw new InvalidInputException("Publication year cannot be in the future.");
        }

        AuthorService authorService = AuthorService.getInstance();
        authorService.getAuthor(book.getAuthorId());
    }
}
