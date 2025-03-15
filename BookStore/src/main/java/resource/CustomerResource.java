package resource;

import exceptions.CustomerNotFoundException;
import exceptions.InvalidInputException;
import model.Customer;
import service.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private final CustomerService customerService = CustomerService.getInstance();

    @POST
    public Response addCustomer(Customer customer) throws InvalidInputException {
        customerService.addCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @GET
    public Response getCustomers() {
        return Response.status(Response.Status.OK).entity(customerService.getCustomers()).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") Long id) throws CustomerNotFoundException {
        return Response.status(Response.Status.OK).entity(customerService.getCustomer(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") Long id, Customer updatedCustomer) throws InvalidInputException, CustomerNotFoundException {
        return Response.status(Response.Status.OK).entity(customerService.updateCustomer(id, updatedCustomer)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) throws CustomerNotFoundException {
        customerService.deleteCustomer(id);
        return Response.status(Response.Status.OK).entity("Customer " + id + " deleted successfully").build();
    }
}
