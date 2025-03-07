package resource;


import exceptions.*;
import model.Order;
import service.OrderService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    OrderService orderService = OrderService.getInstance();

    @POST
    public Response placeOrder(@PathParam("customerId") Long customerId) throws CustomerNotFoundException, CartNotFoundException {
        Order order = orderService.placeOrder(customerId);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @GET
    public Response getOrders(@PathParam("customerId") Long customerId) throws OrderNotFoundException {
        return Response.status(Response.Status.OK).entity(orderService.getOrders(customerId)).build();
    }

    @GET
    @Path("/{orderId}")
    public Response getOrder(@PathParam("customerId") Long customerId, @PathParam("orderId") Long orderId) throws OrderNotFoundException {
        return Response.status(Response.Status.OK).entity(orderService.getOrder(customerId, orderId)).build();
    }
}
