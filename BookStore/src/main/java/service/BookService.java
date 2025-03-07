package service;

import exceptions.BookNotFoundException;
import exceptions.InvalidInputException;
import model.Book;

import javax.ws.rs.core.Response;
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

    public void addBook(Book book) throws InvalidInputException {
        if (book == null || book.getBookId() == null || book.getAuthorId() == null || book.getTitle() == null ||
                book.getPrice() == 0) {
            throw new InvalidInputException("Information is not enough. The following fields are needed: " +
                    "bookId, authorId, title, price");
        }
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
        throw new BookNotFoundException("Book with" + id + "not found");
    }

    public Book updateBook(Long id, Book updatedBook) throws BookNotFoundException, InvalidInputException {
        if (updatedBook == null || updatedBook.getBookId() == null || updatedBook.getAuthorId() == null ||
                updatedBook.getTitle() == null || updatedBook.getPrice() == 0) {
            throw new InvalidInputException("Information is not enough. The following fields are needed: " +
                    "bookId, authorId, title, price");
        }
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
        throw new BookNotFoundException("Book with" + id + "not found");
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookId().equals(id)) {
                books.remove(i);
                return;
            }
        }
        throw new BookNotFoundException("Book with ID " + id + " not found");
    }
}
