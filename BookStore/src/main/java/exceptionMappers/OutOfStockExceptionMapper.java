package exceptionMappers;

import exceptions.OutOfStockException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException> {
    @Override
    public Response toResponse(OutOfStockException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"Out of stock: " + exception.getMessage() + "\"}")
                .type("application/json")
                .build();
    }
}
