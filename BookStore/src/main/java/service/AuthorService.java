package service;

import exceptions.AuthorNotFoundException;
import exceptions.BookNotFoundException;
import exceptions.InvalidInputException;
import model.Author;
import model.Book;

import java.util.ArrayList;

public class AuthorService {
    private static AuthorService instance;
    private ArrayList<Author> authors;
    private final BookService bookService = BookService.getInstance();

    private AuthorService() {
        authors = new ArrayList<>();
    }

    public static AuthorService getInstance() {
        if(instance == null) {
            instance = new AuthorService();
        }
        return instance;
    }


    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) throws InvalidInputException {
        if (author == null) {
            throw new InvalidInputException("Author information is missing.");
        }

        for (Author existingauthor : authors) {
            if (existingauthor.getAuthorId().equals(author.getAuthorId())) {
                throw new InvalidInputException("Author ID already exists.");
            }
        }

        if (author.getAuthorId() == null || author.getAuthorId().toString().trim().isEmpty()) {
            throw new InvalidInputException("authorId shouldn't be empty.");
        }

        if (author.getFirstName() == null || author.getFirstName().trim().isEmpty()) {
            throw new InvalidInputException("Author's first name shouldn't be empty.");
        }

        if (author.getLastName() == null || author.getLastName().trim().isEmpty()) {
            throw new InvalidInputException("Author's last name shouldn't be empty.");
        }
        authors.add(author);
    }

    public Author getAuthor(Long id) throws AuthorNotFoundException {
        for(Author author: authors) {
            if(author.getAuthorId().equals(id)) {
                return author;
            }
        }

        throw new AuthorNotFoundException("Author with id: " + id + " not found");
    }

    public Author updateAuthor(Long id, Author updatedAuthor) throws AuthorNotFoundException, InvalidInputException {
        if (updatedAuthor == null) {
            throw new InvalidInputException("Author information is missing.");
        }
        for (Author existingAuthor : authors) {
            if (existingAuthor.getAuthorId().equals(updatedAuthor.getAuthorId())) {
                throw new InvalidInputException("Author ID already exists.");
            }
        }

        if (updatedAuthor.getAuthorId() == null || updatedAuthor.getAuthorId().toString().trim().isEmpty()) {
            throw new InvalidInputException("authorId shouldn't be empty.");
        }

        if (updatedAuthor.getFirstName() == null || updatedAuthor.getFirstName().trim().isEmpty()) {
            throw new InvalidInputException("Author's first name shouldn't be empty.");
        }

        if (updatedAuthor.getLastName() == null || updatedAuthor.getLastName().trim().isEmpty()) {
            throw new InvalidInputException("Author's last name shouldn't be empty.");
        }
        for(Author author: authors) {
            if(author.getAuthorId().equals(id)) {
                author.setFirstName(updatedAuthor.getFirstName());
                author.setLastName(updatedAuthor.getLastName());
                author.setBiography(updatedAuthor.getBiography());
                return author;
            }
        }
        throw new AuthorNotFoundException("Author with id: " + id + " not found");
    }

    public void deleteAuthor(Long id) throws AuthorNotFoundException, InvalidInputException {
        Author author = getAuthor(id);

        for (Book book : bookService.getBooks()) {
            if (book.getAuthorId().equals(id)) {
                throw new InvalidInputException("Cannot delete author with id: " + id + " as they have books assigned.");
            }
        }
        authors.remove(author);
    }

    public ArrayList<Book> getAuthorBooks(Long id) throws BookNotFoundException, AuthorNotFoundException {
        boolean authorIdExists = false;
        for(Author author: authors) {
            if(author.getAuthorId().equals(id)) {
                authorIdExists = true;
                break;
            }
        }
        if (!authorIdExists) {
            throw new AuthorNotFoundException("Author with id: " + id + " not found");
        }

        ArrayList<Book>  booksOfAuthor = new ArrayList<>();
        for(Book book: bookService.getBooks()) {
            if(book.getAuthorId().equals(id)) {
                booksOfAuthor.add(book);
            }
        }

        if (booksOfAuthor.isEmpty()) {
            throw new BookNotFoundException("Books with author id: " + id + " not found");
        }

        return booksOfAuthor;
    }
}
