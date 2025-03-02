package resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Book;

import java.util.ArrayList;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    ArrayList<Book> books = new ArrayList<>();

    @POST
    public Response addBook(Book book) {
        books.add(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

}
