package exceptionMappers;

import exceptions.InvalidInputException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {
    @Override
    public Response toResponse(InvalidInputException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"Invalid input: " + exception.getMessage() + "\"}")
                .type("application/json")
                .build();
    }
}