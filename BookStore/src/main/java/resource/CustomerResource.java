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
        if (customer == null || customer.getCustomerId() == null || customer.getEmail() == null || customer.getFirstName() == null ||
                customer.getLastName() == null || customer.getPassword() == null) {
            throw new InvalidInputException("Information is not enough. The following fields are needed: " +
                    "customerId, firstName, lastName, email, password");
        }
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
        if (updatedCustomer == null || updatedCustomer.getCustomerId() == null || updatedCustomer.getEmail() == null || updatedCustomer.getFirstName() == null ||
                updatedCustomer.getLastName() == null || updatedCustomer.getPassword() == null) {
            throw new InvalidInputException("Information is not enough. The following fields are needed: " +
                    "customerId, firstName, lastName, email, password");
        }

        return Response.status(Response.Status.OK).entity(customerService.updateCustomer(id, updatedCustomer)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) throws CustomerNotFoundException {
        customerService.deleteCustomer(id);
        return Response.status(Response.Status.OK).entity("Customer " + id + " deleted successfully").build();
    }
}
