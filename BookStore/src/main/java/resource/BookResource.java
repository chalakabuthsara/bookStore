package resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import exceptions.BookNotFoundException;
import exceptions.InvalidInputException;
import model.Book;

import java.io.IOException;
import java.util.ArrayList;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    static ArrayList<Book> books = new ArrayList<>();

    @POST
    public Response addBook(Book book) throws InvalidInputException {
        if (book == null || book.getBookId() == null || book.getAuthorId() == null || book.getTitle() == null ||
                book.getPrice() == 0) {
            throw new InvalidInputException("Information is not enough. The following fields are needed: " +
                    "bookId, authorId, title, price");
        }
        books.add(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @GET
    public Response getBooks(Book book) {
        return Response.status(Response.Status.OK).entity(books).build();
    }

    @GET
    @Path("/{id}")
    public Response getBook(@PathParam("id") Long id) throws BookNotFoundException {
        for(Book book: books) {
            if(book.getBookId().equals(id)) {
                return Response.status(Response.Status.OK).entity(book).build();
            }
        }
        throw new BookNotFoundException("Book with" + id + "not found");
    }

    @PUT
    @Path("/{id")
    public Response updateBook(@PathParam("id") Long id) throws BookNotFoundException {
        for(Book book: books) {
            if(book.getBookId().equals(id)) {
                books.
                return Response.status(Response.Status.OK).entity(book).build();
            }
        }
    }


}
