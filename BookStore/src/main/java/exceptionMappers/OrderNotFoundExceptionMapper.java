package exceptionMappers;

import exceptions.OrderNotFoundException;
import exceptions.OutOfStockException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class OrderNotFoundExceptionMapper implements ExceptionMapper<OrderNotFoundException> {
    @Override
    public Response toResponse(OrderNotFoundException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"Order not found: " + exception.getMessage() + "\"}")
                .type("application/json")
                .build();
    }
}
