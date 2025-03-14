package service;

import exceptions.AuthorNotFoundException;
import exceptions.BookNotFoundException;
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

    public void addAuthor(Author author) {
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

    public Author updateAuthor(Long id, Author updatedAuthor) throws AuthorNotFoundException {
        for(Author author: authors) {
            if(author.getAuthorId().equals(id)) {
                author.setFirstName(updatedAuthor.getFirstName());
                author.setLastName(updatedAuthor.getLastName());
                author.setBiography(updatedAuthor.getBiography());
            }
        }
        throw new AuthorNotFoundException("Author with id: " + id + " not found");
    }

    public void deleteAuthor(Long id) throws AuthorNotFoundException {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getAuthorId().equals(id)) {
                authors.remove(i);
                return;
            }
        }
        throw new AuthorNotFoundException("Author with id: " + id + " not found");
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
