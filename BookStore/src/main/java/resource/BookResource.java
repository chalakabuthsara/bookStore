package resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import exceptions.BookNotFoundException;
import exceptions.InvalidInputException;
import model.Book;
import service.BookService;

import java.io.IOException;
import java.util.ArrayList;


@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookService bookService = BookService.getInstance();

    @POST
    public Response addBook(Book book) throws InvalidInputException {
        if (book == null || book.getBookId() == null || book.getAuthorId() == null || book.getTitle() == null ||
                book.getPrice() == 0) {
            throw new InvalidInputException("Information is not enough. The following fields are needed: " +
                    "bookId, authorId, title, price");
        }
        bookService.addBook(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @GET
    public Response getBooks() {
        return Response.status(Response.Status.OK).entity(bookService.getBooks()).build();
    }

    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") Long id) throws BookNotFoundException {
        return Response.status(Response.Status.OK).entity(bookService.getBook(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") Long id, Book updatedBook) throws BookNotFoundException, InvalidInputException {
        if (updatedBook == null || updatedBook.getBookId() == null || updatedBook.getAuthorId() == null ||
                updatedBook.getTitle() == null || updatedBook.getPrice() == 0) {
            throw new InvalidInputException("Information is not enough. The following fields are needed: " +
                    "bookId, authorId, title, price");
        }

        return Response.status(Response.Status.OK).entity(bookService.updateBook(id, updatedBook)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) throws BookNotFoundException {
        bookService.deleteBook(id);
        return Response.status(Response.Status.OK).entity("Book with " + id + "deleted successfully").build();
    }


}
