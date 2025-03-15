package resource;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import exceptions.BookNotFoundException;
import exceptions.CartNotFoundException;
import exceptions.CustomerNotFoundException;
import exceptions.InvalidInputException;
import exceptions.OutOfStockException;
import service.CartService;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private final CartService cartService = CartService.getInstance();

    @POST
    @Path("/items")
    public Response addItem(@PathParam("customerId") Long customerId, Map<String, Integer> itemData) throws OutOfStockException, BookNotFoundException, CustomerNotFoundException, CartNotFoundException, InvalidInputException {
        Long bookId = Long.valueOf(itemData.get("bookId"));
        Integer quantity = itemData.get("quantity");

        cartService.addItem(customerId, bookId, quantity);
        return Response.status(Response.Status.CREATED).entity(cartService.getCart(customerId)).build();
    }

    @GET
    public Response getItem(@PathParam("customerId") Long customerId) throws CustomerNotFoundException, CartNotFoundException, BookNotFoundException {
        return Response.status(Response.Status.OK).entity(cartService.getItems(customerId)).build();
    }

    @PUT
    @Path("/items/{bookId}")
    public Response updateItem(@PathParam("customerId") Long customerId, @PathParam("bookId") Long bookId, Map<String, Integer> itemData) throws BookNotFoundException, CustomerNotFoundException, CartNotFoundException, InvalidInputException, OutOfStockException {
        Integer newQuantity = itemData.get("quantity");
        cartService.updateItem(customerId, bookId, newQuantity);
        return Response.status(Response.Status.OK).entity(cartService.getCart(customerId)).build();
    }

    @DELETE
    @Path("/items/{bookId}")
    public Response deleteItem(@PathParam("customerId") Long customerId, @PathParam("bookId") Long bookId) throws BookNotFoundException, CustomerNotFoundException, CartNotFoundException {
        cartService.deleteItem(customerId, bookId);
        return Response.status(Response.Status.OK).entity("Book " + bookId + " deleted successfully").build();
    }
}
