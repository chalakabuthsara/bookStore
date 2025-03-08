package exceptionMappers;

import exceptions.AuthorNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorNotFoundExceptionMapper implements ExceptionMapper<AuthorNotFoundException> {
    @Override
    public Response toResponse(AuthorNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"error\": \"Author not found: " + exception.getMessage() + "\"}")
                .type("application/json")
                .build();
    }
}
