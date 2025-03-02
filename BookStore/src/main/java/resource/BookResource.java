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
        books.add(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(Book book) throws BookNotFoundException {
        return Response.status(Response.Status.OK).entity(books).build();
    }

}
