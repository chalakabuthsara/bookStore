package resource;

import exceptions.AuthorNotFoundException;
import exceptions.BookNotFoundException;
import exceptions.InvalidInputException;
import model.Author;
import service.AuthorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final AuthorService authorService = AuthorService.getInstance();

    @POST
    public Response addAuthor(Author author) throws InvalidInputException {
        authorService.addAuthor(author);
        return Response.status(Response.Status.CREATED).entity(author).build();
    }

    @GET
    public Response getAuthors() {
        return Response.status(Response.Status.CREATED).entity(authorService.getAuthors()).build();
    }

    @GET
    @Path("/{id}")
    public Response getAuthor(@PathParam("id") Long id) throws AuthorNotFoundException {
        return Response.status(Response.Status.OK).entity(authorService.getAuthor(id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") Long id, Author updatedAuthor) throws InvalidInputException, AuthorNotFoundException {
        return Response.status(Response.Status.OK).entity(authorService.updateAuthor(id, updatedAuthor)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") Long id) throws AuthorNotFoundException, InvalidInputException {
        authorService.deleteAuthor(id);
        return Response.status(Response.Status.OK).entity("Author with id: " + id + " deleted successfully").build();
    }

    @GET
    @Path("/{id}/books")
    public Response getAuthorBooks(@PathParam("id") Long id) throws AuthorNotFoundException, BookNotFoundException {
        return Response.status(Response.Status.OK).entity(authorService.getAuthorBooks(id)).build();
    }

}
