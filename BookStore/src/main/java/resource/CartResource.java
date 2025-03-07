package resource;

import exceptions.BookNotFoundException;
import exceptions.CartNotFoundException;
import exceptions.CustomerNotFoundException;
import exceptions.OutOfStockException;
import model.Book;
import service.BookService;
import service.CartService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private final CartService cartService = CartService.getInstance();
    private final BookService bookService = BookService.getInstance();

    @POST
    @Path("/items")
    public Response addItem(@PathParam("customerId") Long customerId, Long bookId) throws OutOfStockException, BookNotFoundException, CustomerNotFoundException {
        cartService.addItem(customerId, bookId);
        return Response.status(Response.Status.CREATED).entity(bookService.getBook(bookId)).build();
    }

    @GET
    public Response getItem(@PathParam("customerId") Long customerId) throws CustomerNotFoundException, CartNotFoundException {
        return Response.status(Response.Status.OK).entity(cartService.getItems(customerId)).build();
    }

    @PUT
    @Path("/items/{bookId}")
    public Response updateItem(@PathParam("customerId") Long customerId, @PathParam("bookId") Long bookId, Book updatedBook) throws BookNotFoundException, CustomerNotFoundException, CartNotFoundException {
        return Response.status(Response.Status.OK).entity(cartService.updateItem(customerId, updatedBook, bookId)).build();
    }

    @DELETE
    @Path("/items/{bookId}")
    public Response deleteItem(@PathParam("customerId") Long customerId, @PathParam("bookId") Long bookId) throws BookNotFoundException, CustomerNotFoundException, CartNotFoundException {
        cartService.deleteItem(customerId, bookId);
        return Response.status(Response.Status.OK).entity("Book " + bookId + " deleted successfully").build();
    }
}
